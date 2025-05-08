package com.attendify_admin.home.users.presentation.assign_subject_to_teacher

sealed class AssignSubjectTeacherEvent {
    data class ShowAlertDialog(val message: String) : AssignSubjectTeacherEvent()
    data object DismissAlertDialog : AssignSubjectTeacherEvent()
}