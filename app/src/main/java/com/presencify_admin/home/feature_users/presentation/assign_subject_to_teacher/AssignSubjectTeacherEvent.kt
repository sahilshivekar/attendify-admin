package com.presencify_admin.home.feature_users.presentation.assign_subject_to_teacher

sealed class AssignSubjectTeacherEvent {
    data class SearchQueryChanged(val query: String) : AssignSubjectTeacherEvent()
    data object FetchCourses : AssignSubjectTeacherEvent()
    data class AssignCourse(val courseId: Int) : AssignSubjectTeacherEvent()
}