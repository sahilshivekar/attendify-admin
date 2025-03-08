package com.edu.wiet_admin.users.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun UsersNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UsersDestination.UsersDashboard.route
    ) {
        composable(route = UsersDestination.UsersDashboard.route) {
            Box(contentAlignment = Alignment.Center){
                Text("Users")
            }
        }
    }
}