package com.attendify_admin.feature_admin_auth.presentation.forgot_password

data class ForgotPasswordState(
    val isEmailSent: Boolean = false,
    val emailError: String? = null,
    val isLoading: Boolean = false,
    val email: String = ""
)