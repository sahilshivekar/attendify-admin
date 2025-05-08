package com.attendify_admin.home.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.attendify_admin.common.presentation.components.bottom_bar.AttendifyBottomNavigationBar
import com.attendify_admin.common.presentation.components.top_bar.AttendifyTopAppBar
import com.attendify_admin.common.presentation.components.top_bar.TopAppBarState
import com.attendify_admin.home.navigation.HomeNavHost
import com.attendify_admin.navigation.AppDestination

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScaffold(
    rootNavController: NavHostController,
    topAppBarState: TopAppBarState,
    onDestinationChange: (String?) -> Unit
) {
    val homeNavController = rememberNavController()

    val currentDestinationRoute =
        homeNavController.currentBackStackEntryAsState().value?.destination?.route

    LaunchedEffect(key1 = currentDestinationRoute) {
        onDestinationChange(currentDestinationRoute)
    }

    Scaffold(
        topBar = {
            AttendifyTopAppBar(
                topAppBarState = topAppBarState,
                onProfileIconButtonClick = {
                    rootNavController.navigate(route = AppDestination.AdminMgt.route)
                }
            )

        },

        bottomBar = {
            AttendifyBottomNavigationBar(
                currentDestinationRoute = currentDestinationRoute,
                homeNavController = homeNavController
            )
        },

        ) { paddingValues ->
        HomeNavHost(
            modifier = Modifier.padding(paddingValues),
            homeNavController = homeNavController,
        )
    }
}