package com.attendify_admin.home.users.presentation.assign_student_to_semester

sealed class AssignStudentSemesterEvent {
    data class ShowAlertDialog(val message: String) : AssignStudentSemesterEvent()
    data object DismissAlertDialog : AssignStudentSemesterEvent()
}