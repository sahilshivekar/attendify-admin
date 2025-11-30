package com.presencify_admin.home.feature_users.presentation.modify_student_batch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.home.feature_academics.domain.use_case.GetAllBatchesUseCase
import com.presencify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.presencify_admin.home.feature_users.data.remote.dto.request.ChangeStudentBatchRequest
import com.presencify_admin.home.feature_users.domain.use_case.ChangeStudentBatchUseCase
import com.presencify_admin.home.feature_users.domain.use_case.GetAllStudentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyStudentBatchViewModel @Inject constructor(
    private val getAllBatchesUseCase: GetAllBatchesUseCase,
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val changeStudentBatchUseCase: ChangeStudentBatchUseCase,
    getBranchesUseCase: GetBranchesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ModifyStudentBatchState())
    val state = _state.asStateFlow()

    init {
        getBranchesUseCase(searchQuery = null).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(areBranchesLoading = false)
                    }
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Error fetching branches"
                            )
                        )
                    }
                }

                is Resource.Loading -> {
                    // do nothing let it load in the background until the user is filling above details
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            branchOptions = result.data.orEmpty().toPersistentList(),
                            areBranchesLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)


    }

    private fun getStudents() {
        _state.value.selectedCurrentBatch?.id?.let { batchId ->
            getAllStudentsUseCase(
                batchId = batchId,
                currentBatch = true
            ).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                areStudentsLoading = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { students ->
                            val studentCards = students.map { student ->
                                val studentBatchId =
                                    student.studentBatches?.find { studentBatch ->
                                        studentBatch.batchId == state.value.selectedCurrentBatch?.id
                                    }?.id
                                val currentBatchStartDate =
                                    student.studentBatches?.find { studentBatch ->
                                        studentBatch.batchId == state.value.selectedCurrentBatch?.id
                                    }?.startDate
                                StudentCardData(
                                    id = student.id,
                                    studentName = "${student.firstName} ${if (student.middleName != null) student.middleName + " " else ""}${student.lastName}",
                                    studentImageUrl = student.studentImgUrl,
                                    studentBatchId = studentBatchId,
                                    currentBatchStartDate = currentBatchStartDate
                                )
                            }.toPersistentList()
                            _state.update {
                                it.copy(
                                    areStudentsLoading = false,
                                    students = studentCards
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Error fetching students"
                            )
                        )
                        _state.update {
                            it.copy(
                                areStudentsLoading = false,
                            )
                        }
                    }

                }
            }.launchIn(viewModelScope)
        }
    }

    private fun changeStudentBatch(studentBatchId: Int) {
        val semesterId = _state.value.selectedCurrentBatch?.id
        semesterId?.let {
            changeStudentBatchUseCase(
                requestBody = ChangeStudentBatchRequest(
                    studentBatchId = studentBatchId,
                    batchId = state.value.selectedNewBatch?.id!!,
                    newBatchStartDate = state.value.newBatchStartDate!!
                )
            ).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                students = it.students.map { studentCard ->
                                    if (studentCard.studentBatchId == studentBatchId) {
                                        studentCard.copy(
                                            isChanging = true,
                                            isFailedToChange = false,
                                            isChanged = false
                                        )
                                    } else {
                                        studentCard.copy()
                                    }
                                }.toPersistentList()
                            )
                        }
                    }

                    is Resource.Success -> {

                        _state.update {
                            it.copy(
                                students = it.students.map { studentCard ->
                                    if (studentCard.studentBatchId == studentBatchId) {
                                        studentCard.copy(
                                            isChanging = false,
                                            isFailedToChange = false,
                                            isChanged = true,
                                            supportingText = ""
                                        )
                                    } else {
                                        studentCard.copy()
                                    }
                                }.toPersistentList()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                students = it.students.map { studentCard ->
                                    if (studentCard.studentBatchId == studentBatchId) {
                                        studentCard.copy(
                                            isChanging = false,
                                            isFailedToChange = true,
                                            isChanged = false,
                                            supportingText = result.message ?: ""
                                        )
                                    } else {
                                        studentCard.copy()
                                    }
                                }.toPersistentList()
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    fun onEvent(event: ModifyStudentBatchEvent) {

        when (event) {

            is ModifyStudentBatchEvent.FetchMatchingBatchesForCurrentBatch -> {
                val semester = _state.value.selectedSemesterNumberForCurrentBatch
                val academicYear = _state.value.selectedAcademicYearForCurrentBatch
                val branchId = _state.value.selectedBranchForCurrentBatch?.id

                if (semester == null) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "Please select a semester number"))
                    }
                    return
                }
                if (academicYear == null) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "Please select an academic year"))
                    }
                    return
                }
                if (branchId == null) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "Please select a branch"))
                    }
                    return
                }

                val startYear = academicYear.split("-")[0].trim().toIntOrNull()
                val endYear = academicYear.split("-")[1].trim().toIntOrNull()


                if (startYear != null && endYear != null) {
                    getAllBatchesUseCase(
                        semesterNumber = semester,
                        academicStartYear = startYear,
                        academicEndYear = endYear,
                        branchId = branchId,
                        searchQuery = null
                    ).onEach { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _state.update {
                                    it.copy(
                                        areBatchesLoadingForCurrentBatch = true,
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        areBatchesLoadingForCurrentBatch = false,
                                        foundBatchesForCurrentBatch = result.data
                                            ?: persistentListOf(),
                                    )
                                }
                                result.data?.let {
                                    if (it.isEmpty()) {
                                        SnackbarController.sendEvent(
                                            SnackbarEvent(
                                                message = "No batch found for selected details"
                                            )
                                        )
                                    }
                                }
                            }

                            is Resource.Error -> {
                                SnackbarController.sendEvent(
                                    SnackbarEvent(
                                        message = result.message
                                            ?: "Error fetching batch details"
                                    )
                                )
                                _state.update {
                                    it.copy(
                                        areBatchesLoadingForCurrentBatch = false,
                                    )
                                }
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is ModifyStudentBatchEvent.AcademicYearDropdownVisibilityChangedForCurrentBatch -> {
                _state.update {
                    it.copy(
                        isAcademicYearDropdownExpandedForCurrentBatch = event.expanded
                    )
                }
            }


            is ModifyStudentBatchEvent.BranchDropdownVisibilityChangedForCurrentBatch -> {
                _state.update { it.copy(isBranchDropdownExpandedForCurrentBatch = event.expanded) }
            }

            is ModifyStudentBatchEvent.SemesterDropdownVisibilityChangedForCurrentBatch -> {
                _state.update { it.copy(isSemesterDropdownExpandedForCurrentBatch = event.expanded) }
            }

            is ModifyStudentBatchEvent.AcademicYearSelectedForCurrentBatch -> {
                _state.update {
                    it.copy(
                        selectedAcademicYearForCurrentBatch = event.year,
                        isAcademicYearDropdownExpandedForCurrentBatch = false
                    )
                }
            }

            is ModifyStudentBatchEvent.BranchSelectedForCurrentBatch -> {
                _state.update {
                    it.copy(
                        selectedBranchForCurrentBatch = event.branch,
                        isBranchDropdownExpandedForCurrentBatch = false
                    )
                }
            }


            is ModifyStudentBatchEvent.CurrentBatchSelected -> {
                _state.update {
                    it.copy(
                        selectedCurrentBatch = event.batch,
                        currStep = 2
                    )
                }
            }

            is ModifyStudentBatchEvent.SemesterNumberSelectedForCurrentBatch -> {
                _state.update { it.copy(selectedSemesterNumberForCurrentBatch = event.semesterNumber) }
            }

            is ModifyStudentBatchEvent.ChangeStudentBatchClicked -> {
                changeStudentBatch(event.studentBatchId)
            }

            is ModifyStudentBatchEvent.DateChanged -> {
                _state.update {
                    it.copy(
                        newBatchStartDate = event.date
                    )
                }
            }

            ModifyStudentBatchEvent.DatePickerVisibilityChanged -> {
                _state.update {
                    it.copy(
                        isDatePickerVisible = !state.value.isDatePickerVisible
                    )
                }
            }

            is ModifyStudentBatchEvent.NewBatchSelected -> {
                if (_state.value.selectedNewBatch == event.batch) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "New Batch can't be same as current batch"))
                    }
                }
                _state.update {
                    it.copy(
                        selectedNewBatch = event.batch,
                        currStep = 3
                    )
                }
                getStudents()
            }

            ModifyStudentBatchEvent.BackClicked -> {
                _state.update {
                    it.copy(
                        currStep = state.value.currStep - 1
                    )
                }
            }

            ModifyStudentBatchEvent.NextClicked -> {
                _state.update {
                    it.copy(
                        currStep = state.value.currStep + 1
                    )
                }
            }


            is ModifyStudentBatchEvent.FetchMatchingBatchesForNewBatch -> {
                val semester = _state.value.selectedSemesterNumberForNewBatch
                val academicYear = _state.value.selectedAcademicYearForNewBatch
                val branchId = _state.value.selectedBranchForNewBatch?.id

                if (semester == null) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "Please select a semester number"))
                    }
                    return
                }
                if (academicYear == null) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "Please select an academic year"))
                    }
                    return
                }
                if (branchId == null) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "Please select a branch"))
                    }
                    return
                }

                val startYear = academicYear.split("-")[0].trim().toIntOrNull()
                val endYear = academicYear.split("-")[1].trim().toIntOrNull()


                if (startYear != null && endYear != null) {
                    getAllBatchesUseCase(
                        semesterNumber = semester,
                        academicStartYear = startYear,
                        academicEndYear = endYear,
                        branchId = branchId,
                        searchQuery = null
                    ).onEach { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _state.update {
                                    it.copy(
                                        areBatchesLoadingForNewBatch = true,
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        areBatchesLoadingForNewBatch = false,
                                        foundBatchesForNewBatch = result.data
                                            ?: persistentListOf(),
                                    )
                                }
                                result.data?.let {
                                    if (it.isEmpty()) {
                                        SnackbarController.sendEvent(
                                            SnackbarEvent(
                                                message = "No batch found for selected details"
                                            )
                                        )
                                    }
                                }
                            }

                            is Resource.Error -> {
                                SnackbarController.sendEvent(
                                    SnackbarEvent(
                                        message = result.message
                                            ?: "Error fetching batch details"
                                    )
                                )
                                _state.update {
                                    it.copy(
                                        areBatchesLoadingForNewBatch = false,
                                    )
                                }
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is ModifyStudentBatchEvent.AcademicYearDropdownVisibilityChangedForNewBatch -> {
                _state.update {
                    it.copy(
                        isAcademicYearDropdownExpandedForNewBatch = event.expanded
                    )
                }
            }


            is ModifyStudentBatchEvent.BranchDropdownVisibilityChangedForNewBatch -> {
                _state.update { it.copy(isBranchDropdownExpandedForNewBatch = event.expanded) }
            }

            is ModifyStudentBatchEvent.SemesterDropdownVisibilityChangedForNewBatch -> {
                _state.update { it.copy(isSemesterDropdownExpandedForNewBatch = event.expanded) }
            }

            is ModifyStudentBatchEvent.AcademicYearSelectedForNewBatch -> {
                _state.update {
                    it.copy(
                        selectedAcademicYearForNewBatch = event.year,
                        isAcademicYearDropdownExpandedForNewBatch = false
                    )
                }
            }

            is ModifyStudentBatchEvent.BranchSelectedForNewBatch -> {
                _state.update {
                    it.copy(
                        selectedBranchForNewBatch = event.branch,
                        isBranchDropdownExpandedForNewBatch = false
                    )
                }
            }

            is ModifyStudentBatchEvent.SemesterNumberSelectedForNewBatch -> {
                _state.update { it.copy(selectedSemesterNumberForNewBatch = event.semesterNumber) }
            }
        }
    }
}
