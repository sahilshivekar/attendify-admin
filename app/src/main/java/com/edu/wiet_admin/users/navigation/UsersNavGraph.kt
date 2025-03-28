package com.edu.wiet_admin.users.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edu.wiet_admin.users.presentation.add_staff.AddStaffScreen
import com.edu.wiet_admin.users.presentation.add_staff.AddStaffViewModel
import com.edu.wiet_admin.users.presentation.add_student.AddStudentScreen
import com.edu.wiet_admin.users.presentation.add_student.AddStudentViewModel
import com.edu.wiet_admin.users.presentation.search_staff.SearchStaffScreen
import com.edu.wiet_admin.users.presentation.search_staff.SearchStaffViewModel
import com.edu.wiet_admin.users.presentation.search_student.SearchStudentScreen
import com.edu.wiet_admin.users.presentation.search_student.SearchStudentViewModel
import com.edu.wiet_admin.users.presentation.user_dashboard.UserDashboardScreen

@Composable
fun UsersNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UsersDestination.UsersDashboard.route
    ) {
        composable(route = UsersDestination.UsersDashboard.route) {
            UserDashboardScreen(
                onAddStudentClick = {
                    navController.navigate(UsersDestination.AddStudent.route)
                },
                onSearchStudentClick = {
                    navController.navigate(UsersDestination.SearchStudent.route)
                },
                onAddStaffClick = {
                    navController.navigate(UsersDestination.AddStaff.route)
                },
                onSearchStaffClick = {
                    navController.navigate(UsersDestination.SearchStaff.route)
                },
                onAssignStudentToSemesterClick = {},
                onRemoveStudentFromSemesterClick = {},
                onAssignStudentToDivisionClick = {},
                onModifyStudentDivisionClick = {},
                onAssignStudentToBatchClick = {},
                onModifyStudentBatchClick = {}
            )
        }

        composable(route = UsersDestination.AddStudent.route) {
            val viewModel = hiltViewModel<AddStudentViewModel>()
            AddStudentScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsState().value,
                onAddStudentSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = UsersDestination.SearchStudent.route) {
            val viewModel = hiltViewModel<SearchStudentViewModel>()
            SearchStudentScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsState().value,
            )
        }

        composable(route = UsersDestination.AddStaff.route){
            val viewModel = hiltViewModel<AddStaffViewModel>()
            AddStaffScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsState().value,
                onAddStaffSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = UsersDestination.SearchStaff.route){
            val viewModel = hiltViewModel<SearchStaffViewModel>()
            SearchStaffScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsState().value,
            )
        }
    }
}