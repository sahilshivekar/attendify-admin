package com.presencify_admin.home.feature_users.presentation.remove_from_dropout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.home.feature_users.domain.use_case.GetStudentDetailsByIdUseCase
import com.presencify_admin.home.feature_users.domain.use_case.RemoveStudentFromDropoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RemoveFromDropoutViewModel @Inject constructor(
    private val getStudentDetailsByIdUseCase: GetStudentDetailsByIdUseCase,
    private val removeStudentFromDropoutUseCase: RemoveStudentFromDropoutUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RemoveFromDropoutState())
    val state = _state.asStateFlow()

    fun onStudentIdsReceived(studentIds: List<Int>) {
        _state.update {
            it.copy(
                selectedStudentIds = studentIds.toPersistentList(),
                areStudentsLoading = true,
            )
        }
        fetchStudentDetails()
    }

    private fun fetchStudentDetails() {

        _state.value.selectedStudentIds.forEach { studentId ->
            getStudentDetailsByIdUseCase(studentId).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        // do nothing
                    }

                    is Resource.Success -> {
                        result.data?.let { student ->
                            val studentCard = StudentData(
                                id = student.id,
                                studentName = "${student.firstName} ${if (student.middleName != null) student.middleName + " " else ""}${student.lastName}",
                                studentImageUrl = student.studentImgUrl
                            )
                            _state.update {
                                it.copy(
                                    selectedStudents = _state.value.selectedStudents.add(studentCard)
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Error fetching student details"
                            )
                        )

                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun removeStudentFromDropout(studentId: Int) {
        val academicYear = _state.value.selectedDropoutAcademicYear
        if (academicYear == null) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent(message = "Please select an academic year"))
            }
            return
        }
        val startYear = academicYear.split("-")[0].trim().toIntOrNull()
        val endYear = academicYear.split("-")[1].trim().toIntOrNull()
        removeStudentFromDropoutUseCase(
            studentId = studentId,
            academicStartYear = startYear!!,
            academicEndYear = endYear!!
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            selectedStudents = it.selectedStudents.map { studentData ->
                                if (studentData.id == studentId) {
                                    studentData.copy(
                                        isRemoved = false,
                                        isRemoving = true,
                                        isFailedToRemove = false
                                    )
                                } else {
                                    studentData
                                }
                            }.toPersistentList()
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            selectedStudents = it.selectedStudents.map { studentData ->
                                if (studentData.id == studentId) {
                                    studentData.copy(
                                        isRemoved = true,
                                        isRemoving = false,
                                        isFailedToRemove = false
                                    )
                                } else {
                                    studentData
                                }
                            }.toPersistentList()
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            selectedStudents = it.selectedStudents.map { studentData ->
                                if (studentData.id == studentId) {
                                    studentData.copy(
                                        isRemoved = false,
                                        isRemoving = false,
                                        isFailedToRemove = true,
                                        supportingText = result.message
                                            ?: "Error removing student from dropout"
                                    )
                                } else {
                                    studentData
                                }
                            }.toPersistentList()
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: RemoveFromDropoutEvent) {
        when (event) {
            is RemoveFromDropoutEvent.AcademicYearChanged -> {
                _state.update {
                    it.copy(
                        selectedDropoutAcademicYear = event.newAcademicYear
                    )
                }

            }

            is RemoveFromDropoutEvent.AcademicYearDropdownVisibilityChanged -> {
                _state.update {
                    it.copy(
                        isDropoutAcademicYearDropdownExpanded = event.isVisible
                    )
                }
            }

            is RemoveFromDropoutEvent.RemoveStudentFromDropout -> {
                removeStudentFromDropout(event.studentId)
            }
        }
    }

}