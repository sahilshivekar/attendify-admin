package com.edu.wiet_admin.admin_mgt.presentation.update_password

sealed class UpdatePasswordEvent {
    data class PasswordChanged(val password: String): UpdatePasswordEvent()

    data class ConfirmPasswordChanged(val confirmPassword: String): UpdatePasswordEvent()

    data object UpdatePasswordClicked : UpdatePasswordEvent()

    data object DismissAlertDialog : UpdatePasswordEvent()

    data class PasswordVisibilityChanged(val isVisible: Boolean): UpdatePasswordEvent()
}