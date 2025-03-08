package com.edu.wiet_admin.navigation

sealed class AppDestination(
    val route: String
) {

    data object AdminAuth : AppDestination("admin_auth")

    data object AdminMgt : AppDestination("admin_mgt")

    data object Users : AppDestination("users")

    data object Academics : AppDestination("academics")

    data object Announcements : AppDestination("announcements")

    data object Schedule : AppDestination("schedule")

}