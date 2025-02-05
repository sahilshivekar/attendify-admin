package com.edu.wiet_admin.navigation

import kotlinx.serialization.Serializable

sealed class AppDestination(
    val route: String
) {

    data object AdminAuth : AppDestination("admin_auth")

    data object HomeScreen : AppDestination("home_screen")

    data object AdminDetailsScreen : AppDestination("admin_details_screen")

}