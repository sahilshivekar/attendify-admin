package com.presencify_admin.home.feature_users.presentation.unassign_subject_to_teacher

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class UnassignSubjectTeacherState(
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
    val isUnassigningCourse: Boolean = false,
    val isUnassigned: Boolean = false,
    val isFailedToUnassign: Boolean = false,
    val courseCode: String,
    val courseName: String,
    val schemeName: String?,
    val supportingText: String = "",
    val teacherCourseId: Int
)