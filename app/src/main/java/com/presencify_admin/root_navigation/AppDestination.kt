package com.presencify_admin.root_navigation

sealed class AppDestination(
    val route: String
) {

    data object AdminAuth : AppDestination("admin_auth")

    data object AdminMgt : AppDestination("admin_mgt")

    data object HomeScaffold : AppDestination("home_scaffold")

}
