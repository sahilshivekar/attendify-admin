package com.presencify_admin.feature_admin_mgt.presentation.admin_details

sealed class AdminDetailsEvent {
    data object UpdateDetailsClicked : AdminDetailsEvent()

    data object EditDetailsClicked : AdminDetailsEvent()

    data object CancelEditingDetailsClicked : AdminDetailsEvent()

    data object LogoutClicked : AdminDetailsEvent()

    data object VerifyEmailClicked : AdminDetailsEvent()

    data class EmailChanged(val email: String) : AdminDetailsEvent()

    data class UsernameChanged(val username: String) : AdminDetailsEvent()

    data class PasswordVisibilityChanged(val isVisible: Boolean) : AdminDetailsEvent()

    data object RemoveAdminClicked: AdminDetailsEvent()

    data object DismissRemoveAccountClicked: AdminDetailsEvent()
}