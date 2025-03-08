package com.edu.wiet_admin.academics.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AcademicsNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AcademicsDestination.AcademicsDashboard.route
    ) {
        composable(route = AcademicsDestination.AcademicsDashboard.route) {
            Box(contentAlignment = Alignment.Center){
                Text("Academics")
            }
        }
    }
}