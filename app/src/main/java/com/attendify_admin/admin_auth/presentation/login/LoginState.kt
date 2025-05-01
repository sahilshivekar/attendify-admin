package com.attendify_admin.admin_auth.presentation.login

data class LoginState(
    val emailOrUsername: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val isEmailOrUsernameEnabled: Boolean = true,
    val isPasswordEnabled: Boolean = true,
    val isLoginButtonEnabled: Boolean = true,
    val isForgottenPasswordEnabled: Boolean = true,
    val emailOrUsernameError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val isOtherError: String? = null
    )