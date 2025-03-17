package com.edu.wiet_admin.users.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edu.wiet_admin.users.presentation.add_student.AddStudentScreen
import com.edu.wiet_admin.users.presentation.add_student.AddStudentViewModel
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
                onEditStudentClick = {},
                onRemoveStudentClick = {},
                onSearchStudentClick = {},
                onAddStaffClick = {},
                onEditStaffClick = {},
                onRemoveStaffClick = {},
                onSearchStaffClick = {},
                onAssignStudentToSemesterClick = {},
                onRemoveStudentFromSemesterClick = {},
                onAssignStudentToDivisionClick = {},
                onModifyStudentDivisionClick = {},
                onRemoveStudentFromDivisionClick = {},
                onAssignStudentToBatchClick = {},
                onModifyStudentBatchClick = {},
                onRemoveStudentFromBatchClick = {}
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
    }
}