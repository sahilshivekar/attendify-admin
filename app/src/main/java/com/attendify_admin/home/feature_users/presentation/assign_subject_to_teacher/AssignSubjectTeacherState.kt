package com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class AssignSubjectTeacherState(
    val staffName: String? = null,
    val staffRole: String? = null,
    val staffHighesQualification: String? = null,
    val staffImageUrl: String? = null,
    val staffId: Int? = null,
    val isLoadingStaffDetails: Boolean = false,
    val searchQuery: String = "",
    val courses: PersistentList<CourseData> = persistentListOf(),
    val isLoadingCourses: Boolean = false,
)


data class CourseData(
    val courseId: Int,
    val isAssigningCourse: Boolean = false,
    val isAssigned: Boolean = false,
    val isFailedToAssign: Boolean = false,
    val courseCode: String,
    val courseName: String,
    val schemeName: String?,
    val supportingText: String = ""
)