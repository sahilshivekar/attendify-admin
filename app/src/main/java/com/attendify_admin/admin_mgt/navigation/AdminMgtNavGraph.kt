package com.attendify_admin.admin_mgt.navigation

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.attendify_admin.admin_auth.navigation.AuthDestination
import com.attendify_admin.admin_mgt.presentation.add_admin.AddAdminScreen
import com.attendify_admin.admin_mgt.presentation.add_admin.AddAdminViewModel
import com.attendify_admin.admin_mgt.presentation.admin_details.AdminDetailsScreen
import com.attendify_admin.admin_mgt.presentation.admin_details.AdminDetailsViewModel
import com.attendify_admin.admin_mgt.presentation.update_password.UpdatePasswordScreen
import com.attendify_admin.admin_mgt.presentation.update_password.UpdatePasswordViewModel
import com.attendify_admin.navigation.AppDestination


fun NavGraphBuilder.adminMgtNavGraph(
    navController: NavController
) {

    navigation(
        route = AppDestination.AdminMgt.route,
        startDestination = AdminMgtDestination.AdminDetails.route
    ) {

        composable(
            route = AdminMgtDestination.AdminDetails.route
        )
        {

            val viewModel = hiltViewModel<AdminDetailsViewModel>()
            val state = viewModel.state.collectAsState().value

            AdminDetailsScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToUpdatePassword = {
                    navController.navigate(AdminMgtDestination.UpdatePassword.route)
                },
                navigateToVerifyCode = {
                    navController.navigate(
                        route = AuthDestination.VerifyCodeScreen.route.replace(
                            oldValue = "{email}",
                            newValue = state.orgEmail
                        )
                    )
                },
                navigateToAddAdmin = {
                    navController.navigate(AdminMgtDestination.AddAdmin.route)
                },
            )
        }

        composable(
            route = AdminMgtDestination.UpdatePassword.route
        )
        {
            val viewModel = hiltViewModel<UpdatePasswordViewModel>()
            val state = viewModel.state.collectAsState().value

            UpdatePasswordScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToAdminDetailsScreen = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = AdminMgtDestination.AddAdmin.route
        )
        {
            val viewModel = hiltViewModel<AddAdminViewModel>()
            val state = viewModel.state.collectAsState().value

            AddAdminScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToAdminDetailsScreen = {
                    navController.navigateUp()
                }
            )
        }
    }


}