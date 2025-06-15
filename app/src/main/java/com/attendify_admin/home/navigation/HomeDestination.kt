package com.attendify_admin.home.navigation

sealed class HomeDestination(
    val route: String
) {
    data object Users : HomeDestination(route = "users")

    data object Academics : HomeDestination(route = "academics")

    data object Schedule : HomeDestination(route = "schedule")
}