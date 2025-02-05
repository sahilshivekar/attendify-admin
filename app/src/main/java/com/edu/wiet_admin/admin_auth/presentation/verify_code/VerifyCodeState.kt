package com.edu.wiet_admin.admin_auth.presentation.verify_code

data class VerifyCodeState (
    val code: String = "",
    val codeError: String? = null,
    val isVerified: Boolean = false,
    val isLoading: Boolean = false,
    val email: String = "",
    val isOtherError: String? = null
)