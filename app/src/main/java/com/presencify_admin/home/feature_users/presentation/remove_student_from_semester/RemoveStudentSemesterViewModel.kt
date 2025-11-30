package com.presencify_admin.home.feature_users.presentation.remove_student_from_semester

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.home.feature_academics.domain.use_case.GetAllSemestersUseCase
import com.presencify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.presencify_admin.home.feature_users.domain.use_case.GetAllStudentsUseCase
import com.presencify_admin.home.feature_users.domain.use_case.RemoveStudentFromSemesterUseCase
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
class RemoveStudentSemesterViewModel @Inject constructor(
    private val getAllSemestersUseCase: GetAllSemestersUseCase,
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val removeStudentFromSemesterUseCase: RemoveStudentFromSemesterUseCase,
    getBranchesUseCase: GetBranchesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(RemoveStudentSemesterState())
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
        _state.value.selectedSemester?.id?.let { semesterId ->
            getAllStudentsUseCase(semesterId = semesterId).onEach { result ->
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
                                val studentSemesterId =
                                    student.studentSemesters?.find { studentSemester ->
                                        studentSemester.semesterId == state.value.selectedSemester?.id
                                    }?.id
                                StudentCard(
                                    id = student.id,
                                    studentName = "${student.firstName} ${if (student.middleName != null) student.middleName + " " else ""}${student.lastName}",
                                    studentImageUrl = student.studentImgUrl,
                                    studentSemesterId = studentSemesterId
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

    private fun unassignStudentFromSemester(studentSemesterId: Int) {
        val semesterId = _state.value.selectedSemester?.id
        semesterId?.let {
            removeStudentFromSemesterUseCase(
                studentSemesterId
            ).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                students = it.students.map { studentCard ->
                                    if (studentCard.studentSemesterId == studentSemesterId) {
                                        studentCard.copy(
                                            isUnassigning = true,
                                            isFailedToUnassign = false,
                                            isUnassigned = false
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
                                    if (studentCard.studentSemesterId == studentSemesterId) {
                                        studentCard.copy(
                                            isUnassigning = false,
                                            isFailedToUnassign = false,
                                            isUnassigned = true,
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
                                    if (studentCard.studentSemesterId == studentSemesterId) {
                                        studentCard.copy(
                                            isUnassigning = false,
                                            isFailedToUnassign = true,
                                            isUnassigned = false,
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

    fun onEvent(event: RemoveStudentSemesterEvent) {

        when (event) {

            is RemoveStudentSemesterEvent.FetchMatchingSemesters -> {
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

            is RemoveStudentSemesterEvent.AcademicYearDropdownVisibilityChanged -> {
                _state.update {
                    it.copy(
                        isAcademicYearDropdownExpanded = event.expanded
                    )
                }
            }


            is RemoveStudentSemesterEvent.BranchDropdownVisibilityChanged -> {
                _state.update { it.copy(isBranchDropdownExpanded = event.expanded) }
            }

            is RemoveStudentSemesterEvent.SemesterDropdownVisibilityChanged -> {
                _state.update { it.copy(isSemesterDropdownExpanded = event.expanded) }
            }

            is RemoveStudentSemesterEvent.AcademicYearSelected -> {
                _state.update {
                    it.copy(
                        selectedAcademicYear = event.year,
                        isAcademicYearDropdownExpanded = false
                    )
                }
            }

            is RemoveStudentSemesterEvent.BranchSelected -> {
                _state.update {
                    it.copy(
                        selectedBranch = event.branch,
                        isBranchDropdownExpanded = false
                    )
                }
            }


            is RemoveStudentSemesterEvent.SemesterSelected -> {
                _state.update { it.copy(selectedSemester = event.semester) }
                getStudents()
            }

            is RemoveStudentSemesterEvent.SemesterNumberSelected -> {
                _state.update { it.copy(selectedSemesterNumber = event.semesterNumber) }
            }

            is RemoveStudentSemesterEvent.UnassignStudentClicked -> {
                unassignStudentFromSemester(event.studentSemesterId)
            }

        }
    }
}
