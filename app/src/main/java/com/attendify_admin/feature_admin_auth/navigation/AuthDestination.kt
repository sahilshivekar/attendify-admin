package com.attendify_admin.feature_admin_auth.navigation

sealed class AuthDestination(
    val route: String
) {

    data object LoginScreen : AuthDestination("login_screen")

    data object ForgotPasswordScreen : AuthDestination("forgot_password_screen")

    data object VerifyCodeScreen : AuthDestination("verify_code_screen/{email}")

}

