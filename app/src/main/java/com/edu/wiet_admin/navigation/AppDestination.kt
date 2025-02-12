package com.edu.wiet_admin.navigation

sealed class AppDestination(
    val route: String
) {

    data object AdminAuth : AppDestination("admin_auth")

    data object HomeScreen : AppDestination("home_screen")

    data object AdminMgt : AppDestination("admin_mgt")

}