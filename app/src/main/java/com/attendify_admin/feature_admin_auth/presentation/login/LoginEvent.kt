package com.attendify_admin.feature_admin_auth.presentation.login

sealed class LoginEvent {
    data class EmailOrUsernameChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class PasswordVisibilityChanged(val isVisible: Boolean) : LoginEvent()
    data object LoginClicked : LoginEvent()
    data object DismissAlertDialog: LoginEvent()
}