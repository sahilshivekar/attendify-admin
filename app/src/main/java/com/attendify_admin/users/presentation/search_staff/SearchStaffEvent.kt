package com.attendify_admin.users.presentation.search_staff

sealed class SearchStaffEvent {
    data class SearchQueryChanged(val searchQuery: String) : SearchStaffEvent()
    data object FetchStaff : SearchStaffEvent()
    data object DismissAlertDialog : SearchStaffEvent()
    data class ShowAlertDialog(val message: String) : SearchStaffEvent()
}