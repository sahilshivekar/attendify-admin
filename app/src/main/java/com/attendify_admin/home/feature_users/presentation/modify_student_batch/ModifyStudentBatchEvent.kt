package com.attendify_admin.home.feature_users.presentation.modify_student_batch

sealed class ModifyStudentBatchEvent {
    data class ShowAlertDialog(val message: String) : ModifyStudentBatchEvent()
    data object DismissAlertDialog : ModifyStudentBatchEvent()
}