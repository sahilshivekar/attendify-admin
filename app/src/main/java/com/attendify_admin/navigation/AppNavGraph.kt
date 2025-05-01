package com.attendify_admin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.attendify_admin.academics.navigation.AcademicsNavHost
import com.attendify_admin.admin_auth.navigation.adminAuthNavGraph
import com.attendify_admin.admin_mgt.navigation.adminMgtNavGraph
import com.attendify_admin.announcements.navigation.AnnouncementsNavHost
import com.attendify_admin.shedule.navigation.ScheduleNavHost
import com.attendify_admin.users.navigation.UsersNavHost

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

        adminAuthNavGraph(navController)

        adminMgtNavGraph(navController)

        composable(AppDestination.Users.route) {
            UsersNavHost()
        }

        composable(AppDestination.Schedule.route) {
            ScheduleNavHost()
        }

        composable(AppDestination.Announcements.route) {
            AnnouncementsNavHost()
        }

        composable(AppDestination.Academics.route) {
            AcademicsNavHost()
        }

    }

}


