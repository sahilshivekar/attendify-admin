package com.attendify_admin.feature_admin_auth.presentation.forgot_password

sealed class ForgotPasswordEvent {
    data class EmailChanged(val email: String) : ForgotPasswordEvent()
    data object SendCodeClicked : ForgotPasswordEvent()
    data object DismissAlertDialog: ForgotPasswordEvent()
}