package com.presencify_admin.root_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.presencify_admin.common.presentation.components.top_bar.TopAppBarState
import com.presencify_admin.feature_admin_auth.navigation.adminAuthNavGraph
import com.presencify_admin.feature_admin_mgt.navigation.adminMgtNavGraph
import com.presencify_admin.home.presentation.HomeScaffold

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PresencifyNavHost(
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
            HomeScaffold(
                rootNavController = rootNavController,
                topAppBarState = TopAppBarState(
                    isProfileIconButtonVisible = true,
                    isTopAppBarVisible = true,
                    isAppLogoNameVisible = true
                )
            )
        }
    }
}


