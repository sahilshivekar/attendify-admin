package com.attendify_admin.home.feature_users.presentation.modify_student_division

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.domain.use_case.GetAllDivisionsUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_users.data.remote.dto.request.ChangeStudentDivisionRequest
import com.attendify_admin.home.feature_users.domain.use_case.ChangeStudentDivisionUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetAllStudentsUseCase
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
class ModifyStudentDivisionViewModel @Inject constructor(
    private val getAllDivisionsUseCase: GetAllDivisionsUseCase,
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val changeStudentDivisionUseCase: ChangeStudentDivisionUseCase,
    getBranchesUseCase: GetBranchesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ModifyStudentDivisionState())
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
        _state.value.selectedCurrentDivision?.id?.let { divisionId ->
            getAllStudentsUseCase(
                divisionId = divisionId,
                currentDivision = true
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
                                val studentDivisionId =
                                    student.studentDivisions?.find { studentDivision ->
                                        studentDivision.divisionId == state.value.selectedCurrentDivision?.id
                                    }?.id
                                val currentDivisionStartDate =
                                    student.studentDivisions?.find { studentDivision ->
                                        studentDivision.divisionId == state.value.selectedCurrentDivision?.id
                                    }?.startDate
                                StudentCardData(
                                    id = student.id,
                                    studentName = "${student.firstName} ${if (student.middleName != null) student.middleName + " " else ""}${student.lastName}",
                                    studentImageUrl = student.studentImgUrl,
                                    studentDivisionId = studentDivisionId,
                                    currentDivisionStartDate = currentDivisionStartDate
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

    private fun changeStudentDivision(studentDivisionId: Int) {
        val semesterId = _state.value.selectedCurrentDivision?.id
        semesterId?.let {
            changeStudentDivisionUseCase(
                requestBody = ChangeStudentDivisionRequest(
                    studentDivisionId = studentDivisionId,
                    divisionId = state.value.selectedNewDivision?.id!!,
                    newDivisionStartDate = state.value.newDivisionStartDate!!
                )
            ).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                students = it.students.map { studentCard ->
                                    if (studentCard.studentDivisionId == studentDivisionId) {
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
                                    if (studentCard.studentDivisionId == studentDivisionId) {
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
                                    if (studentCard.studentDivisionId == studentDivisionId) {
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

    fun onEvent(event: ModifyStudentDivisionEvent) {

        when (event) {

            is ModifyStudentDivisionEvent.FetchMatchingDivisionsForCurrentDivision -> {
                val semester = _state.value.selectedSemesterNumberForCurrentDivision
                val academicYear = _state.value.selectedAcademicYearForCurrentDivision
                val branchId = _state.value.selectedBranchForCurrentDivision?.id

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
                    getAllDivisionsUseCase(
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
                                        areDivisionsLoadingForCurrentDivision = true,
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        areDivisionsLoadingForCurrentDivision = false,
                                        foundDivisionsForCurrentDivision = result.data
                                            ?: persistentListOf(),
                                    )
                                }
                                result.data?.let {
                                    if (it.isEmpty()) {
                                        SnackbarController.sendEvent(
                                            SnackbarEvent(
                                                message = "No division found for selected details"
                                            )
                                        )
                                    }
                                }
                            }

                            is Resource.Error -> {
                                SnackbarController.sendEvent(
                                    SnackbarEvent(
                                        message = result.message
                                            ?: "Error fetching division details"
                                    )
                                )
                                _state.update {
                                    it.copy(
                                        areDivisionsLoadingForCurrentDivision = false,
                                    )
                                }
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is ModifyStudentDivisionEvent.AcademicYearDropdownVisibilityChangedForCurrentDivision -> {
                _state.update {
                    it.copy(
                        isAcademicYearDropdownExpandedForCurrentDivision = event.expanded
                    )
                }
            }


            is ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForCurrentDivision -> {
                _state.update { it.copy(isBranchDropdownExpandedForCurrentDivision = event.expanded) }
            }

            is ModifyStudentDivisionEvent.SemesterDropdownVisibilityChangedForCurrentDivision -> {
                _state.update { it.copy(isSemesterDropdownExpandedForCurrentDivision = event.expanded) }
            }

            is ModifyStudentDivisionEvent.AcademicYearSelectedForCurrentDivision -> {
                _state.update {
                    it.copy(
                        selectedAcademicYearForCurrentDivision = event.year,
                        isAcademicYearDropdownExpandedForCurrentDivision = false
                    )
                }
            }

            is ModifyStudentDivisionEvent.BranchSelectedForCurrentDivision -> {
                _state.update {
                    it.copy(
                        selectedBranchForCurrentDivision = event.branch,
                        isBranchDropdownExpandedForCurrentDivision = false
                    )
                }
            }


            is ModifyStudentDivisionEvent.CurrentDivisionSelected -> {
                _state.update {
                    it.copy(
                        selectedCurrentDivision = event.division,
                        currStep = 2
                    )
                }
            }

            is ModifyStudentDivisionEvent.SemesterNumberSelectedForCurrentDivision -> {
                _state.update { it.copy(selectedSemesterNumberForCurrentDivision = event.semesterNumber) }
            }

            is ModifyStudentDivisionEvent.ChangeStudentDivisionClicked -> {
                changeStudentDivision(event.studentDivisionId)
            }

            is ModifyStudentDivisionEvent.DateChanged -> {
                _state.update {
                    it.copy(
                        newDivisionStartDate = event.date
                    )
                }
            }

            ModifyStudentDivisionEvent.DatePickerVisibilityChanged -> {
                _state.update {
                    it.copy(
                        isDatePickerVisible = !state.value.isDatePickerVisible
                    )
                }
            }

            is ModifyStudentDivisionEvent.NewDivisionSelected -> {
                if (_state.value.selectedNewDivision == event.division) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent(message = "New Division can't be same as current division"))
                    }
                }
                _state.update {
                    it.copy(
                        selectedNewDivision = event.division,
                        currStep = 3
                    )
                }
                getStudents()
            }

            ModifyStudentDivisionEvent.BackClicked -> {
                _state.update {
                    it.copy(
                        currStep = state.value.currStep - 1
                    )
                }
            }

            ModifyStudentDivisionEvent.NextClicked -> {
                _state.update {
                    it.copy(
                        currStep = state.value.currStep + 1
                    )
                }
            }


            is ModifyStudentDivisionEvent.FetchMatchingDivisionsForNewDivision -> {
                val semester = _state.value.selectedSemesterNumberForNewDivision
                val academicYear = _state.value.selectedAcademicYearForNewDivision
                val branchId = _state.value.selectedBranchForNewDivision?.id

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
                    getAllDivisionsUseCase(
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
                                        areDivisionsLoadingForNewDivision = true,
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        areDivisionsLoadingForNewDivision = false,
                                        foundDivisionsForNewDivision = result.data
                                            ?: persistentListOf(),
                                    )
                                }
                                result.data?.let {
                                    if (it.isEmpty()) {
                                        SnackbarController.sendEvent(
                                            SnackbarEvent(
                                                message = "No division found for selected details"
                                            )
                                        )
                                    }
                                }
                            }

                            is Resource.Error -> {
                                SnackbarController.sendEvent(
                                    SnackbarEvent(
                                        message = result.message
                                            ?: "Error fetching division details"
                                    )
                                )
                                _state.update {
                                    it.copy(
                                        areDivisionsLoadingForNewDivision = false,
                                    )
                                }
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is ModifyStudentDivisionEvent.AcademicYearDropdownVisibilityChangedForNewDivision -> {
                _state.update {
                    it.copy(
                        isAcademicYearDropdownExpandedForNewDivision = event.expanded
                    )
                }
            }


            is ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForNewDivision -> {
                _state.update { it.copy(isBranchDropdownExpandedForNewDivision = event.expanded) }
            }

            is ModifyStudentDivisionEvent.SemesterDropdownVisibilityChangedForNewDivision -> {
                _state.update { it.copy(isSemesterDropdownExpandedForNewDivision = event.expanded) }
            }

            is ModifyStudentDivisionEvent.AcademicYearSelectedForNewDivision -> {
                _state.update {
                    it.copy(
                        selectedAcademicYearForNewDivision = event.year,
                        isAcademicYearDropdownExpandedForNewDivision = false
                    )
                }
            }

            is ModifyStudentDivisionEvent.BranchSelectedForNewDivision -> {
                _state.update {
                    it.copy(
                        selectedBranchForNewDivision = event.branch,
                        isBranchDropdownExpandedForNewDivision = false
                    )
                }
            }

            is ModifyStudentDivisionEvent.SemesterNumberSelectedForNewDivision -> {
                _state.update { it.copy(selectedSemesterNumberForNewDivision = event.semesterNumber) }
            }
        }
    }
}
