package com.attendify_admin.home.shedule.navigation

sealed class ScheduleDestination(
    val route: String
) {
    data object ScheduleDashboard : ScheduleDestination("schedule_dashboard")
}