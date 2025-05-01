package com.attendify_admin.announcements.navigation

sealed class AnnouncementsDestination(
    val route: String
) {
    data object AnnouncementsDashboard : AnnouncementsDestination("announcements_dashboard")
}