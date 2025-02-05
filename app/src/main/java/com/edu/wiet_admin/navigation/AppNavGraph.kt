package com.edu.wiet_admin.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edu.wiet_admin.admin_auth.navigation.adminAuthGraph
import com.edu.wiet_admin.common.presentation.scaffold.top_bar.TopAppBarViewModel

@Composable
fun AppNavGraph(
    startDestination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        adminAuthGraph(navController)
    
        composable(route = AppDestination.HomeScreen.route) {
            Box(
                modifier = Modifier.background(Color.Red).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Hello")
            }
        }
    }

}


