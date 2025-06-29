package com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.domain.use_case.GetAllCoursesUseCase
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddTeachingSubjectRequest
import com.attendify_admin.home.feature_users.domain.use_case.AddTeachingSubjectUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStaffByIdUseCase
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
class AssignSubjectTeacherViewModel @Inject constructor(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val assignSubjectTeacherUseCase: AddTeachingSubjectUseCase,
    private val getStaffByIdUseCase: GetStaffByIdUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AssignSubjectTeacherState())
    val state = _state.asStateFlow()

    init {
        getCourses()
    }

    fun onStaffIdReceived(staffId: Int) {
        _state.update {
            it.copy(
                staffId = staffId
            )
        }
        getStaffById()
    }

    private fun getStaffById() {
        getStaffByIdUseCase(
            staffId = state.value.staffId!!,
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = result.message ?: "Failed to fetch staff details"
                        )
                    )
                    _state.update {
                        it.copy(
                            isLoadingStaffDetails = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoadingStaffDetails = true
                        )
                    }
                }

                is Resource.Success -> {
                    result.data?.let { staff ->
                        _state.update {
                            it.copy(
                                staffName = staff.firstName + staff.lastName,
                                staffImageUrl = staff.staffImageUrl,
                                staffRole = staff.role,
                                staffHighesQualification = staff.highestQualification,
                                isLoadingStaffDetails = false
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun assignCourse(courseId: Int) {
        if (_state.value.staffName == null) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent("Select a staff member to assign a subject"))
            }
            return
        }
        assignSubjectTeacherUseCase(
            requestBody = AddTeachingSubjectRequest(
                staffId = state.value.staffId!!,
                courseId = courseId
            ),
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            courses = _state.value.courses.map { courseData ->
                                if (courseData.courseId == courseId) {
                                    courseData.copy(
                                        isAssigned = false,
                                        isAssigningCourse = false,
                                        isFailedToAssign = true,
                                        supportingText = result.message ?: "Failed to assign subject"
                                    )
                                } else {
                                    courseData
                                }
                            }.toPersistentList()
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            courses = _state.value.courses.map { courseData ->
                                if (courseData.courseId == courseId) {
                                    courseData.copy(
                                        isAssigned = false,
                                        isAssigningCourse = true,
                                        isFailedToAssign = false,
                                        supportingText = ""

                                    )
                                } else {
                                    courseData
                                }
                            }.toPersistentList()
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            courses = _state.value.courses.map { courseData ->
                                if (courseData.courseId == courseId) {
                                    courseData.copy(
                                        isAssigned = true,
                                        isAssigningCourse = false,
                                        isFailedToAssign = false,
                                        supportingText = ""
                                    )
                                } else {
                                    courseData
                                }
                            }.toPersistentList()
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getCourses() {
        getAllCoursesUseCase(
            searchQuery = _state.value.searchQuery,
            branchId = null,
            semesterNumber = null,
            schemeId = null,
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = result.message ?: "Failed to fetch courses"
                        )
                    )
                    _state.update {
                        it.copy(
                            isLoadingCourses = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoadingCourses = true
                        )
                    }
                }

                is Resource.Success -> {
                    result.data?.let { courses ->
                        if (courses.isEmpty()) {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = "No courses found for this query"
                                )
                            )
                        }
                        _state.update {
                            it.copy(
                                isLoadingCourses = false,
                                courses = courses.map { course ->
                                    CourseData(
                                        courseId = course.id,
                                        courseCode = course.code,
                                        courseName = course.name,
                                        schemeName = course.scheme?.name
                                    )
                                }.toPersistentList()
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: AssignSubjectTeacherEvent) {
        when (event) {
            is AssignSubjectTeacherEvent.AssignCourse -> {
                assignCourse(event.courseId)
            }

            AssignSubjectTeacherEvent.FetchCourses -> {
                getCourses()
            }

            is AssignSubjectTeacherEvent.SearchQueryChanged -> {
                _state.update {
                    it.copy(
                        searchQuery = event.query
                    )
                }
            }
        }
    }
}