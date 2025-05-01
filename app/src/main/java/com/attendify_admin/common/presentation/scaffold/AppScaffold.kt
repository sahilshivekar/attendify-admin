package com.attendify_admin.common.presentation.scaffold

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.attendify_admin.common.presentation.scaffold.bottom_bar.AttendifyBottomNavigationBar
import com.attendify_admin.common.presentation.scaffold.top_bar.AttendifyTopAppBar
import com.attendify_admin.navigation.AppDestination
import com.attendify_admin.navigation.AppNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppScaffold(
    startDestination: String
) {

    val rootNavController = rememberNavController()
    val currDest = rootNavController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        topBar = {
            AttendifyTopAppBar(
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
                AttendifyBottomNavigationBar(
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