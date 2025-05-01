package com.attendify_admin.admin_auth.presentation.forgot_password

data class ForgotPasswordState(
    val isEmailSent: Boolean = false,
    val emailError: String? = null,
    val isLoading: Boolean = false,
    val email: String = "",
    val isOtherError: String? = null
)