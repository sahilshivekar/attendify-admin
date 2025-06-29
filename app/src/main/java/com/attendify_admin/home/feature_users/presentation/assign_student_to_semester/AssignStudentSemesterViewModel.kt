package com.attendify_admin.home.feature_users.presentation.assign_student_to_semester

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.domain.use_case.GetAllSemestersUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddStudentToSemesterRequest
import com.attendify_admin.home.feature_users.domain.use_case.AddStudentToSemesterUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentDetailsByIdUseCase
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
class AssignStudentSemesterViewModel @Inject constructor(
    private val getAllSemestersUseCase: GetAllSemestersUseCase,
    private val getStudentDetailsByIdUseCase: GetStudentDetailsByIdUseCase,
    private val addStudentToSemesterUseCase: AddStudentToSemesterUseCase,
    getBranchesUseCase: GetBranchesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AssignStudentSemesterState())
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


    fun onStudentIdsReceived(studentIds: List<Int>) {
        _state.update {
            it.copy(
                selectedStudentIds = studentIds.toPersistentList(),
                areStudentsLoading = true,
                studentsToLoad = studentIds.size
            )
        }

        fetchStudentDetails()
    }


    private fun fetchStudentDetails() {

        _state.value.selectedStudentIds.forEach { studentId ->
            getStudentDetailsByIdUseCase(studentId).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                areStudentsLoading = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { student ->
                            val studentCard = StudentCard(
                                id = student.id,
                                studentName = "${student.firstName} ${if (student.middleName != null) student.middleName + " " else ""}${student.lastName}",
//                                studentBranch = student.branch?.abbreviation ?: "",
//                                studentYear = if (
//                                    (student.studentSemesters?.size ?: 0) > 0 &&
//                                    student.studentSemesters?.first()?.semester?.semesterNumber != null
//                                )
//                                    StudentUtils.getCurrentYearFromSem(
//                                        student.studentSemesters.first().semester.semesterNumber
//                                    ) else null,
                                studentImageUrl = student.studentImgUrl
                            )
                            _state.update {
                                it.copy(
                                    selectedStudents = _state.value.selectedStudents.add(studentCard)
                                )
                            }
                            if (_state.value.selectedStudents.size == _state.value.studentsToLoad) {
                                _state.update {
                                    it.copy(
                                        areStudentsLoading = false
                                    )
                                }
                            }
                        }
                    }

                    is Resource.Error -> {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Error fetching student details"
                            )
                        )
                        _state.update {
                            it.copy(
                                studentsToLoad = _state.value.studentsToLoad - 1
                            )
                        }
                    }

                }
            }.launchIn(viewModelScope)
        }
    }

    private fun assignStudentsToSemester() {
        val semesterId = _state.value.selectedSemester?.id
        semesterId?.let {
            _state.value.selectedStudentIds.forEach { studentId ->
                addStudentToSemesterUseCase(
                    requestBody = AddStudentToSemesterRequest(
                        studentId = studentId,
                        semesterId = semesterId
                    )
                ).onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            var student = _state.value.selectedStudents.find { student ->
                                student.id == studentId
                            }
                            student?.let {
                                _state.update {
                                    it.copy(
                                        selectedStudents = _state.value.selectedStudents.remove(
                                            student
                                        )
                                    )
                                }
                            }

                            student?.let {
                                student = student.copy(isAdding = true)
                                _state.update {
                                    it.copy(
                                        selectedStudents = _state.value.selectedStudents.add(
                                            student
                                        )
                                    )
                                }
                            }

                            _state.update {
                                it.copy(
                                    isAssigningStudents = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            result.data?.let { studentSemester ->
                                var student = _state.value.selectedStudents.find { student ->
                                    student.id == studentId
                                }
                                student?.let {
                                    _state.update {
                                        it.copy(
                                            selectedStudents = _state.value.selectedStudents.remove(
                                                student
                                            )
                                        )
                                    }

                                }
                                student?.let {
                                    student = student.copy(isAdded = true, isAdding = false)
                                    _state.update {
                                        it.copy(
                                            selectedStudents = _state.value.selectedStudents.add(
                                                student
                                            )
                                        )
                                    }
                                }

                                _state.update {
                                    it.copy(
                                        studentsAssignOrFailedToAssignCount = _state.value.studentsAssignOrFailedToAssignCount + 1,
                                        isAssigningStudents = _state.value.studentsAssignOrFailedToAssignCount < _state.value.studentsToLoad,
                                        isAssigningDone = _state.value.studentsAssignOrFailedToAssignCount == _state.value.studentsToLoad
                                    )
                                }
                            }
                        }

                        is Resource.Error -> {
//                            SnackbarController.sendEvent(
//                                SnackbarEvent(
//                                    message = result.message ?: "Error fetching student details"
//                                )
//                            )
                            _state.update {
                                it.copy(
                                    studentsToLoad = _state.value.studentsToLoad - 1
                                )
                            }

                            var student = _state.value.selectedStudents.find { student ->
                                student.id == studentId
                            }
                            student?.let {
                                _state.update {
                                    it.copy(
                                        selectedStudents = _state.value.selectedStudents.remove(
                                            student
                                        )
                                    )
                                }
                            }
                            student?.let {
                                student = student.copy(
                                    isFailedToAdd = true,
                                    isAdding = false,
                                    supportingText = result.message ?: ""
                                )
                                _state.update {
                                    it.copy(
                                        selectedStudents = _state.value.selectedStudents.add(
                                            student
                                        )
                                    )
                                }
                            }
                            _state.update {
                                it.copy(
                                    studentsAssignOrFailedToAssignCount = _state.value.studentsAssignOrFailedToAssignCount + 1,
                                    isAssigningStudents = _state.value.studentsAssignOrFailedToAssignCount < _state.value.studentsToLoad,
                                    isAssigningDone = _state.value.studentsAssignOrFailedToAssignCount == _state.value.studentsToLoad
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onEvent(event: AssignStudentSemesterEvent) {

        when (event) {

            is AssignStudentSemesterEvent.FetchMatchingSemesters -> {
                val semester = _state.value.selectedSemesterNumber
                val academicYear = _state.value.selectedAcademicYear
                val branchId = _state.value.selectedBranch?.id

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
                    getAllSemestersUseCase(
                        semesterNumber = semester,
                        academicStartYear = startYear,
                        academicEndYear = endYear,
                        branchId = branchId
                    ).onEach { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _state.update {
                                    it.copy(
                                        areSemestersLoading = true,
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        areSemestersLoading = false,
                                        foundSemesters = result.data ?: persistentListOf(),
                                    )
                                }
                                result.data?.let {
                                    if (it.isEmpty()) {
                                        SnackbarController.sendEvent(
                                            SnackbarEvent(
                                                message = "No semester found for selected semester details"
                                            )
                                        )
                                    }
                                }
                            }

                            is Resource.Error -> {
                                SnackbarController.sendEvent(
                                    SnackbarEvent(
                                        message = result.message
                                            ?: "Error fetching semester details"
                                    )
                                )
                                _state.update {
                                    it.copy(
                                        areSemestersLoading = false,
                                    )
                                }
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is AssignStudentSemesterEvent.AcademicYearDropdownVisibilityChanged -> {
                _state.update {
                    it.copy(
                        isAcademicYearDropdownExpanded = event.expanded
                    )
                }
            }


            is AssignStudentSemesterEvent.BranchDropdownVisibilityChanged -> {
                _state.update { it.copy(isBranchDropdownExpanded = event.expanded) }
            }

            is AssignStudentSemesterEvent.SemesterDropdownVisibilityChanged -> {
                _state.update { it.copy(isSemesterDropdownExpanded = event.expanded) }
            }

            is AssignStudentSemesterEvent.AcademicYearSelected -> {
                _state.update {
                    it.copy(
                        selectedAcademicYear = event.year,
                        isAcademicYearDropdownExpanded = false
                    )
                }
            }

            is AssignStudentSemesterEvent.BranchSelected -> {
                _state.update {
                    it.copy(
                        selectedBranch = event.branch,
                        isBranchDropdownExpanded = false
                    )
                }
            }


            is AssignStudentSemesterEvent.SemesterSelected -> {
                _state.update { it.copy(selectedSemester = event.semester) }
            }

            is AssignStudentSemesterEvent.SemesterNumberSelected -> {
                _state.update { it.copy(selectedSemesterNumber = event.semesterNumber) }
            }

            AssignStudentSemesterEvent.AssignStudentsClicked -> {
                assignStudentsToSemester()
            }

            is AssignStudentSemesterEvent.RemoveStudentClicked -> {
                val student = _state.value.selectedStudents.find { studentCard ->
                    studentCard.id == event.studentId
                }
                student?.let {
                    _state.update {
                        it.copy(selectedStudents = _state.value.selectedStudents.remove(student))
                    }
                }
            }
        }
    }
}
