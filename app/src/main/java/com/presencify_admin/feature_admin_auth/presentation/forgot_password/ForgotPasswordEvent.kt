package com.presencify_admin.feature_admin_auth.presentation.forgot_password

sealed interface ForgotPasswordEvent {
    data class EmailChanged(val email: String) : ForgotPasswordEvent
    data object SendCodeClicked : ForgotPasswordEvent
}