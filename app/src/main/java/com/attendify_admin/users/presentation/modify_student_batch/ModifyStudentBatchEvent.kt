package com.attendify_admin.users.presentation.modify_student_batch

sealed class ModifyStudentBatchEvent {
    data class ShowAlertDialog(val message: String) : ModifyStudentBatchEvent()
    data object DismissAlertDialog : ModifyStudentBatchEvent()
}