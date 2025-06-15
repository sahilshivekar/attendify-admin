package com.attendify_admin.home.feature_users.presentation.unassign_subject_to_teacher

sealed class UnassignSubjectTeacherEvent {
    data class ShowAlertDialog(val message: String) : UnassignSubjectTeacherEvent()
    data object DismissAlertDialog : UnassignSubjectTeacherEvent()
}