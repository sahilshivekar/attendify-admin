//package com.attendify_admin.home.feature_academics.navigation
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideOutHorizontally
//import androidx.compose.runtime.Composable
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.attendify_admin.home.feature_academics.presentation.academics_dashboard.AcademicsDashboardRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_batch.BatchRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_batch.BatchViewModel
//import com.attendify_admin.home.feature_academics.presentation.manage_branch.BranchRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_branch.BranchViewModel
//import com.attendify_admin.home.feature_academics.presentation.manage_course.CourseRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_course.CourseViewModel
//import com.attendify_admin.home.feature_academics.presentation.manage_division.DivisionRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_division.DivisionViewModel
//import com.attendify_admin.home.feature_academics.presentation.manage_scheme.SchemeRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_scheme.SchemeViewModel
//import com.attendify_admin.home.feature_academics.presentation.manage_semester.SemesterRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_semester.SemesterViewModel
//import com.attendify_admin.home.feature_academics.presentation.manage_university.UniversityRoot
//import com.attendify_admin.home.feature_academics.presentation.manage_university.UniversityViewModel
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun AcademicsNavHost() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = AcademicsDestination.AcademicsDashboard.route
//    ) {
//
//        // Dashboard
//        composable(
//            route = AcademicsDestination.AcademicsDashboard.route,
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//        ) {
//            AcademicsDashboardRoot(navController = navController)
//        }
//
//        // Curriculum Structure
//        composable(
//            route = AcademicsDestination.ManageBranch.route,
//            enterTransition = { fadeIn() + slideInHorizontally { it / 2 } },
//            exitTransition = { fadeOut() + slideOutHorizontally { -it / 2 } },
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//            popExitTransition = { fadeOut() + slideOutHorizontally { it / 2 } }
//        ) {
//            val viewModel = hiltViewModel<BranchViewModel>()
//            BranchRoot(viewModel = viewModel)
//        }
//
//        composable(
//            route = AcademicsDestination.ManageCourse.route,
//            enterTransition = { fadeIn() + slideInHorizontally { it / 2 } },
//            exitTransition = { fadeOut() + slideOutHorizontally { -it / 2 } },
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//            popExitTransition = { fadeOut() + slideOutHorizontally { it / 2 } }
//        ) {
//            val viewModel = hiltViewModel<CourseViewModel>()
//            CourseRoot(viewModel = viewModel)
//        }
//
//        composable(
//            route = AcademicsDestination.ManageScheme.route,
//            enterTransition = { fadeIn() + slideInHorizontally { it / 2 } },
//            exitTransition = { fadeOut() + slideOutHorizontally { -it / 2 } },
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//            popExitTransition = { fadeOut() + slideOutHorizontally { it / 2 } }
//        ) {
//            val viewModel = hiltViewModel<SchemeViewModel>()
//            SchemeRoot(viewModel = viewModel)
//        }
//
//        composable(
//            route = AcademicsDestination.ManageUniversity.route,
//            enterTransition = { fadeIn() + slideInHorizontally { it / 2 } },
//            exitTransition = { fadeOut() + slideOutHorizontally { -it / 2 } },
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//            popExitTransition = { fadeOut() + slideOutHorizontally { it / 2 } }
//        ) {
//            val viewModel = hiltViewModel<UniversityViewModel>()
//            UniversityRoot(viewModel = viewModel)
//        }
//
//        // Academic Grouping
//        composable(
//            route = AcademicsDestination.ManageSemester.route,
//            enterTransition = { fadeIn() + slideInHorizontally { it / 2 } },
//            exitTransition = { fadeOut() + slideOutHorizontally { -it / 2 } },
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//            popExitTransition = { fadeOut() + slideOutHorizontally { it / 2 } }
//        ) {
//            val viewModel = hiltViewModel<SemesterViewModel>()
//            SemesterRoot(viewModel = viewModel)
//        }
//
//        composable(
//            route = AcademicsDestination.ManageDivision.route,
//            enterTransition = { fadeIn() + slideInHorizontally { it / 2 } },
//            exitTransition = { fadeOut() + slideOutHorizontally { -it / 2 } },
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//            popExitTransition = { fadeOut() + slideOutHorizontally { it / 2 } }
//        ) {
//            val viewModel = hiltViewModel<DivisionViewModel>()
//            DivisionRoot(viewModel = viewModel)
//        }
//
//        composable(
//            route = AcademicsDestination.ManageBatch.route,
//            enterTransition = { fadeIn() + slideInHorizontally { it / 2 } },
//            exitTransition = { fadeOut() + slideOutHorizontally { -it / 2 } },
//            popEnterTransition = { fadeIn() + slideInHorizontally { -it / 2 } },
//            popExitTransition = { fadeOut() + slideOutHorizontally { it / 2 } }
//        ) {
//            val viewModel = hiltViewModel<BatchViewModel>()
//            BatchRoot(viewModel = viewModel)
//        }
//    }
//}
