package com.presencify_admin.home.feature_users.presentation.unassign_subject_to_teacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.home.feature_users.domain.use_case.GetStaffByIdUseCase
import com.presencify_admin.home.feature_users.domain.use_case.GetTeachingSubjectsUseCase
import com.presencify_admin.home.feature_users.domain.use_case.RemoveTeachingSubjectUseCase
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
class UnassignSubjectTeacherViewModel @Inject constructor(
    private val getTeachingSubjectsUseCase: GetTeachingSubjectsUseCase,
    private val unassignSubjectTeacherUseCase: RemoveTeachingSubjectUseCase,
    private val getStaffByIdUseCase: GetStaffByIdUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(UnassignSubjectTeacherState())
    val state = _state.asStateFlow()

    fun onStaffIdReceived(staffId: Int) {
        _state.update {
            it.copy(
                staffId = staffId
            )
        }
        getStaffById()
        getAssignedCoursesOfStaffMember(staffId)
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

    private fun unassignCourse(teacherSubjectId: Int) {
        if (_state.value.staffName == null) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent("Select a staff member to assign a subject"))
            }
            return
        }
        unassignSubjectTeacherUseCase(
            teacherSubjectId = teacherSubjectId,
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            courses = _state.value.courses.map { courseData ->
                                if (courseData.teacherCourseId == teacherSubjectId) {
                                    courseData.copy(
                                        isUnassigned = false,
                                        isUnassigningCourse = false,
                                        isFailedToUnassign = true,
                                        supportingText = result.message
                                            ?: "Failed to assign subject"
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
                                if (courseData.teacherCourseId == teacherSubjectId) {
                                    courseData.copy(
                                        isUnassigned = false,
                                        isUnassigningCourse = true,
                                        isFailedToUnassign = false,
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
                                if (courseData.teacherCourseId == teacherSubjectId) {
                                    courseData.copy(
                                        isUnassigned = true,
                                        isUnassigningCourse = false,
                                        isFailedToUnassign = false,
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


    private fun getAssignedCoursesOfStaffMember(staffId: Int) {
        getTeachingSubjectsUseCase(staffId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isLoadingCourses = false) }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to load courses assigned to staff members"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoadingCourses = true) }
                }

                is Resource.Success -> {
                    result.data?.let {
                        if (result.data.isEmpty()) {
                            viewModelScope.launch {
                                SnackbarController.sendEvent(
                                    SnackbarEvent(
                                        "No courses assigned to staff member"
                                    )
                                )
                            }
                        }
                    }
                    _state.update {
                        it.copy(
                            isLoadingCourses = false,
                            courses = result.data?.map { teacherTeaches ->
                                CourseData(
                                    courseId = teacherTeaches.course?.id!!,
                                    courseName = teacherTeaches.course.name,
                                    courseCode = teacherTeaches.course.code,
                                    schemeName = teacherTeaches.course.scheme?.name!!,
                                    teacherCourseId = teacherTeaches.id,
                                )
                            }?.toPersistentList() ?: persistentListOf()
                        )
                    }
                }

            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: UnassignSubjectTeacherEvent) {
        when (event) {
            is UnassignSubjectTeacherEvent.UnassignCourse -> {
                unassignCourse(event.teacherSubjectId)
            }

            is UnassignSubjectTeacherEvent.SearchQueryChanged -> {
                _state.update {
                    it.copy(
                        searchQuery = event.query
                    )
                }
            }
        }
    }
}