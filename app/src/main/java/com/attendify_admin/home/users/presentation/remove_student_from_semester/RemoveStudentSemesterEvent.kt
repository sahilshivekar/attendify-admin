package com.attendify_admin.home.users.presentation.remove_student_from_semester

sealed class RemoveStudentSemesterEvent {
    data class ShowAlertDialog(val message: String) : RemoveStudentSemesterEvent()
    data object DismissAlertDialog : RemoveStudentSemesterEvent()
}