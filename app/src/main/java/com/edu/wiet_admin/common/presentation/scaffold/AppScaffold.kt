package com.edu.wiet_admin.common.presentation.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edu.wiet_admin.common.presentation.scaffold.bottom_bar.WietBottomNavigationBar
import com.edu.wiet_admin.common.presentation.scaffold.top_bar.WietTopAppBar
import com.edu.wiet_admin.navigation.AppDestination
import com.edu.wiet_admin.navigation.AppNavGraph

@Composable
fun AppScaffold(
    startDestination: String
) {

    val rootNavController = rememberNavController()
    val currDest = rootNavController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        topBar = {
            WietTopAppBar(
                navController = rootNavController,
                currDest = currDest
            )
        },
        bottomBar = {

            val destinationsToShowBottomBar = listOf(
                AppDestination.Users.route,
                AppDestination.Schedule.route,
                AppDestination.Announcements.route,
                AppDestination.Academics.route
            )

            if(currDest in destinationsToShowBottomBar) {
                WietBottomNavigationBar(
                    currDest = currDest,
                    rootNavController = rootNavController
                )
            }

        },
    ) { paddingValues ->
        AppNavGraph(
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues),
            navController = rootNavController
        )
    }
}