package com.presencify_admin.home.feature_users.presentation.search_staff

sealed class SearchStaffEvent {
    data class SearchQueryChanged(val searchQuery: String) : SearchStaffEvent()
    data object FetchStaff : SearchStaffEvent()
    data class SelectionModeChanged(val isSelectionEnabled: Boolean) : SearchStaffEvent()
}