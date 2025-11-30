package com.presencify_admin.home.feature_schedule.navigation

sealed class ScheduleDestination(
    val route: String
) {
    data object ScheduleDashboard : ScheduleDestination("schedule_dashboard")
}