package com.attendify_admin.admin_mgt.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
    rootNavController: NavController
) {

    navigation(
        route = AppDestination.AdminMgt.route,
        startDestination = AdminMgtDestination.AdminDetails.route
    ) {

        composable(
            route = AdminMgtDestination.AdminDetails.route,
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
        )
        {

            val viewModel = hiltViewModel<AdminDetailsViewModel>()
            val state = viewModel.state.collectAsState().value

            AdminDetailsScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToUpdatePassword = {
                    rootNavController.navigate(AdminMgtDestination.UpdatePassword.route)
                },
                navigateToVerifyCode = {
                    rootNavController.navigate(
                        route = AuthDestination.VerifyCodeScreen.route.replace(
                            oldValue = "{email}",
                            newValue = state.orgEmail
                        )
                    )
                },
                navigateToAddAdmin = {
                    rootNavController.navigate(AdminMgtDestination.AddAdmin.route)
                },
                onBackIconButtonClick = {
                    rootNavController.navigateUp()
                }
            )
        }

        composable(
            route = AdminMgtDestination.UpdatePassword.route,
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
        )
        {
            val viewModel = hiltViewModel<UpdatePasswordViewModel>()
            val state = viewModel.state.collectAsState().value

            UpdatePasswordScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToAdminDetailsScreen = {
                    rootNavController.navigateUp()
                },
                onBackIconButtonClick = {
                    rootNavController.navigateUp()
                }
            )
        }

        composable(
            route = AdminMgtDestination.AddAdmin.route,
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
        )
        {
            val viewModel = hiltViewModel<AddAdminViewModel>()
            val state = viewModel.state.collectAsState().value

            AddAdminScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToAdminDetailsScreen = {
                    rootNavController.navigateUp()
                },
                onBackIconButtonClick = {
                    rootNavController.navigateUp()
                }
            )
        }
    }


}