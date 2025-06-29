package com.attendify_admin.home.feature_users.presentation.unassign_subject_to_teacher

sealed class UnassignSubjectTeacherEvent {
    data class SearchQueryChanged(val query: String) : UnassignSubjectTeacherEvent()
    data class UnassignCourse(val teacherSubjectId: Int) : UnassignSubjectTeacherEvent()
}