package com.attendify_admin.home.feature_users.presentation.assign_student_to_batch

sealed class AssignStudentBatchEvent {
    data class ShowAlertDialog(val message: String) : AssignStudentBatchEvent()
    data object DismissAlertDialog : AssignStudentBatchEvent()
}