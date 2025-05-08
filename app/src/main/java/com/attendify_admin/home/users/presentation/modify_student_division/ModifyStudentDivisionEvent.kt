package com.attendify_admin.home.users.presentation.modify_student_division

sealed class ModifyStudentDivisionEvent {
    data class ShowAlertDialog(val message: String) : ModifyStudentDivisionEvent()
    data object DismissAlertDialog : ModifyStudentDivisionEvent()
}