package com.attendify_admin.feature_admin_auth.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.attendify_admin.feature_admin_auth.presentation.forgot_password.ForgotPasswordScreen
import com.attendify_admin.feature_admin_auth.presentation.forgot_password.ForgotPasswordViewModel
import com.attendify_admin.feature_admin_auth.presentation.login.LoginScreen
import com.attendify_admin.feature_admin_auth.presentation.login.LoginViewModel
import com.attendify_admin.feature_admin_auth.presentation.verify_code.VerifyCodeScreen
import com.attendify_admin.feature_admin_auth.presentation.verify_code.VerifyCodeViewModel
import com.attendify_admin.feature_admin_mgt.navigation.AdminMgtDestination
import com.attendify_admin.root_navigation.AppDestination

fun NavGraphBuilder.adminAuthNavGraph(
    rootNavController: NavController,
) {

    navigation(
        route = AppDestination.AdminAuth.route,
        startDestination = AuthDestination.LoginScreen.route
    ) {

        composable(
            route = AuthDestination.LoginScreen.route,
            enterTransition = {
                fadeIn() + slideInVertically { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            }
        )
        {

            val viewModel = hiltViewModel<LoginViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value

            LoginScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToHomeScreen = {
                    rootNavController.navigate(AppDestination.HomeScaffold.route) {
                        popUpTo(AuthDestination.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToForgotPasswordScreen = {
                    rootNavController.navigate(AuthDestination.ForgotPasswordScreen.route)
                }
            )

        }



        composable(
            route = AuthDestination.ForgotPasswordScreen.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
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

            val viewModel = hiltViewModel<ForgotPasswordViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value

            ForgotPasswordScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navigateToVerifyCodeScreen = {
                    rootNavController.navigate(
                        AuthDestination.VerifyCodeScreen.route + "?email=${state.email}"
                    )
                },
                onBackIconButtonClick = {
                    rootNavController.navigateUp()
                }
            )

        }



        composable(
            route = AuthDestination.VerifyCodeScreen.route + "?email={email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
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
        { backStackEntry ->

            val viewModel = hiltViewModel<VerifyCodeViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value

            VerifyCodeScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navigateToHomeOrAdminDetailsScreen = {
                    val prevDestination =
                        rootNavController.previousBackStackEntry?.destination?.route

                    if (prevDestination == AdminMgtDestination.AdminDetails.route) {
                        rootNavController.navigateUp()
                    } else if (prevDestination == AuthDestination.ForgotPasswordScreen.route) {
                        rootNavController.navigate(AppDestination.HomeScaffold.route) {
                            popUpTo(AuthDestination.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                },
                onBackIconButtonClick = {
                    rootNavController.navigateUp()
                }
            )
        }
    }
}