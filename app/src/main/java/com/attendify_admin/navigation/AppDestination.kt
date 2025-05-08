package com.attendify_admin.navigation

sealed class AppDestination(
    val route: String
) {

    data object AdminAuth : AppDestination("admin_auth")

    data object AdminMgt : AppDestination("admin_mgt")

    data object HomeScaffold : AppDestination("home_scaffold")

}
