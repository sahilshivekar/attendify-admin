package com.attendify_admin.announcements.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AnnouncementsNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AnnouncementsDestination.AnnouncementsDashboard.route
    ) {
        composable(route = AnnouncementsDestination.AnnouncementsDashboard.route) {
            Box(contentAlignment = Alignment.Center){
                Text("Announcements")
            }
        }
    }
}