package com.attendify_admin.admin_auth.navigation

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.attendify_admin.admin_auth.presentation.forgot_password.ForgotPasswordScreen
import com.attendify_admin.admin_auth.presentation.forgot_password.ForgotPasswordViewModel
import com.attendify_admin.admin_auth.presentation.login.LoginScreen
import com.attendify_admin.admin_auth.presentation.login.LoginViewModel
import com.attendify_admin.admin_auth.presentation.verify_code.VerifyCodeScreen
import com.attendify_admin.admin_auth.presentation.verify_code.VerifyCodeViewModel
import com.attendify_admin.admin_mgt.navigation.AdminMgtDestination
import com.attendify_admin.navigation.AppDestination

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
            val state = viewModel.state.collectAsState().value

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

            val viewModel = hiltViewModel<ForgotPasswordViewModel>()
            val state = viewModel.state.collectAsState().value

            ForgotPasswordScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navigateToVerifyCodeScreen = {
                    rootNavController.navigate(
                        AuthDestination.VerifyCodeScreen.route
                            .replace(
                                "{email}",
                                state.email
                            )
                    )
                },
                onBackIconButtonClick = {
                    rootNavController.navigateUp()
                }
            )

        }



        composable(
            route = AuthDestination.VerifyCodeScreen.route,
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
        { backStackEntry ->

            val viewModel = hiltViewModel<VerifyCodeViewModel>()
            val state = viewModel.state.collectAsState().value
            val email = backStackEntry.arguments?.getString("email")

            LaunchedEffect(email) {
                email?.let {
                    Log.d("verifyCode", "adminAuthNavGraph: $it")
                    viewModel.setEmail(it)
                }
            }

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