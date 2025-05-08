package com.attendify_admin.home.announcements.navigation

sealed class AnnouncementsDestination(
    val route: String
) {
    data object AnnouncementsDashboard : AnnouncementsDestination("announcements_dashboard")
}