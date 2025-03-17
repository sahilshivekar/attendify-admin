package com.edu.wiet_admin.users.navigation

sealed class UsersDestination(
    val route: String
) {
    data object UsersDashboard : UsersDestination("users_dashboard")
    data object AddStudent : UsersDestination("add_student")
}