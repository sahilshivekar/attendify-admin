package com.edu.wiet_admin.admin_mgt.presentation.add_admin

sealed class AddAdminEvent {
    data class EmailChanged(val email: String) : AddAdminEvent()
    data class UsernameChanged(val username: String) : AddAdminEvent()
    data class PasswordChanged(val password: String) : AddAdminEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : AddAdminEvent()
    data object AddAdminClicked : AddAdminEvent()
    data object DismissAlertDialog : AddAdminEvent()
    data object PasswordVisibilityChanged : AddAdminEvent()
}