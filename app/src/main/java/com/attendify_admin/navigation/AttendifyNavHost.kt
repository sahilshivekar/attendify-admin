package com.attendify_admin.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.attendify_admin.admin_auth.navigation.adminAuthNavGraph
import com.attendify_admin.admin_mgt.navigation.adminMgtNavGraph
import com.attendify_admin.home.presentation.HomeScaffold
import com.attendify_admin.home.presentation.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AttendifyNavHost(
    modifier: Modifier = Modifier,
    startDestination: String
) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        adminAuthNavGraph(rootNavController = rootNavController)

        adminMgtNavGraph(rootNavController = rootNavController)

        composable(
            route = AppDestination.HomeScaffold.route,
            enterTransition = {
                fadeIn() + slideInHorizontally{ it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScaffold(
                rootNavController = rootNavController,
                topAppBarState = viewModel.state.collectAsStateWithLifecycle().value,
                onDestinationChange = viewModel::updateTopAppBarForRoute
            )
        }
    }
}


