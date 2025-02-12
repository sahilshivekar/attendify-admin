package com.edu.wiet_admin.admin_mgt.navigation

sealed class AdminMgtDestination(
    val route: String
) {

    data object AddAdmin : AdminMgtDestination("add_admin")

    data object UpdatePassword : AdminMgtDestination("update_password")

    data object AdminDetails : AdminMgtDestination("admin_details")

}

