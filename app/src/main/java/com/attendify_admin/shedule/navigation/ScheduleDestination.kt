package com.attendify_admin.shedule.navigation

sealed class ScheduleDestination(
    val route: String
) {
    data object ScheduleDashboard : ScheduleDestination("schedule_dashboard")
}