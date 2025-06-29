package com.attendify_admin.home.feature_users.presentation.add_to_dropout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddDropoutRequest
import com.attendify_admin.home.feature_users.domain.use_case.AddStudentToDropoutUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentDetailsByIdUseCase
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
class AddToDropoutViewModel @Inject constructor(
    private val addStudentToDropoutUseCase: AddStudentToDropoutUseCase,
    private val getStudentDetailsByIdUseCase: GetStudentDetailsByIdUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AddToDropoutState())
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

    private fun addStudentToDropout(studentId: Int) {
        val academicYear = _state.value.selectedDropoutAcademicYear
        if (academicYear == null) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent(message = "Please select an academic year"))
            }
            return
        }
        val startYear = academicYear.split("-")[0].trim().toIntOrNull()
        val endYear = academicYear.split("-")[1].trim().toIntOrNull()
        addStudentToDropoutUseCase(
            requestBody = AddDropoutRequest(
                studentId = studentId,
                academicStartYear = startYear!!,
                academicEndYear = endYear!!
            )
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            selectedStudents = it.selectedStudents.map { studentData ->
                                if (studentData.id == studentId) {
                                    studentData.copy(
                                        isAdded = false,
                                        isAdding = true,
                                        isFailedToAdd = false
                                    )
                                } else {
                                    studentData
                                }
                            }.toPersistentList()
                        )
                    }
                }

                is Resource.Success -> {
                    result.data?.let { dropout ->
                        _state.update {
                            it.copy(
                                selectedStudents = it.selectedStudents.map { studentData ->
                                    if (studentData.id == dropout.studentId) {
                                        studentData.copy(
                                            isAdded = true,
                                            isAdding = false,
                                            isFailedToAdd = false
                                        )
                                    } else {
                                        studentData
                                    }
                                }.toPersistentList()
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            selectedStudents = it.selectedStudents.map { studentData ->
                                if (studentData.id == studentId) {
                                    studentData.copy(
                                        isAdded = false,
                                        isAdding = false,
                                        isFailedToAdd = true,
                                        supportingText = result.message ?: "Error adding student to dropout"
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

    fun onEvent(event: AddToDropoutEvent) {
        when (event) {
            is AddToDropoutEvent.AcademicYearChanged -> {
                _state.update {
                    it.copy(
                        selectedDropoutAcademicYear = event.newAcademicYear
                    )
                }

            }
            is AddToDropoutEvent.AcademicYearDropdownVisibilityChanged -> {
                _state.update {
                    it.copy(
                        isDropoutAcademicYearDropdownExpanded = event.isVisible
                    )
                }
            }
            is AddToDropoutEvent.AddStudentToDropout -> {
                addStudentToDropout(event.studentId)
            }
        }
    }
}
