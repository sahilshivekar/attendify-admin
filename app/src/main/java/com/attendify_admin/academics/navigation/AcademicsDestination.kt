package com.attendify_admin.academics.navigation

sealed class AcademicsDestination(
    val route: String
) {
    data object AcademicsDashboard : AcademicsDestination("academics_dashboard")
}