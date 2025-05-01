package com.attendify_admin.admin_auth.presentation.forgot_password

import com.attendify_admin.admin_auth.presentation.login.LoginEvent

sealed class ForgotPasswordEvent {
    data class EmailChanged(val email: String) : ForgotPasswordEvent()
    data object SendCodeClicked : ForgotPasswordEvent()
    data object DismissAlertDialog: ForgotPasswordEvent()
}