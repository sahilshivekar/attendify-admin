package com.presencify_admin.feature_admin_auth.presentation.login

data class LoginState(
    val emailOrUsername: String = "",
    val emailOrUsernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val isLoading: Boolean = false
    )