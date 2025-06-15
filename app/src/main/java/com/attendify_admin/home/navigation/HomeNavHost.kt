package com.attendify_admin.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.attendify_admin.home.feature_academics.navigation.AcademicsNavHost
import com.attendify_admin.home.feature_schedule.navigation.ScheduleNavHost
import com.attendify_admin.home.feature_users.navigation.UsersNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    homeNavController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = homeNavController,
        startDestination = HomeDestination.Users.route
    ) {
        composable(
            route = HomeDestination.Users.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            UsersNavHost()
        }

        composable(
            route = HomeDestination.Schedule.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            ScheduleNavHost()
        }

        composable(
            route = HomeDestination.Academics.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            AcademicsNavHost()
        }
    }
}