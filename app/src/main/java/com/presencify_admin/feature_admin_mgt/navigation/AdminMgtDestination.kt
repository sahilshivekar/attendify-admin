package com.presencify_admin.feature_admin_mgt.navigation

sealed class AdminMgtDestination(
    val route: String
) {

    data object AddAdmin : AdminMgtDestination("add_admin")

    data object UpdatePassword : AdminMgtDestination("update_password")

    data object AdminDetails : AdminMgtDestination("admin_details")

}

