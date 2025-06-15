package com.attendify_admin.home.feature_users.presentation.assign_student_to_division

sealed class AssignStudentDivisionEvent {
    data class ShowAlertDialog(val message: String) : AssignStudentDivisionEvent()
    data object DismissAlertDialog : AssignStudentDivisionEvent()
}