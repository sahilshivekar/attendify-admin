package com.attendify_admin.home.shedule.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ScheduleNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScheduleDestination.ScheduleDashboard.route
    ) {
        composable(route = ScheduleDestination.ScheduleDashboard.route) {
            Box(contentAlignment = Alignment.Center){
                Text("Schedule")
            }
        }
    }
}