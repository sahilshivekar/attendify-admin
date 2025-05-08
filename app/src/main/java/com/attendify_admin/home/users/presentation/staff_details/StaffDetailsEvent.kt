package com.attendify_admin.home.users.presentation.staff_details

sealed class StaffDetailsEvent {
    data class ShowAlertDialog(val message: String) : StaffDetailsEvent()
    data object DismissAlertDialog : StaffDetailsEvent()
}