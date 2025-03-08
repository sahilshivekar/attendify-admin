package com.edu.wiet_admin.admin_auth.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.edu.wiet_admin.admin_auth.presentation.forgot_password.ForgotPasswordScreen
import com.edu.wiet_admin.admin_auth.presentation.forgot_password.ForgotPasswordViewModel
import com.edu.wiet_admin.admin_auth.presentation.login.LoginScreen
import com.edu.wiet_admin.admin_auth.presentation.login.LoginViewModel
import com.edu.wiet_admin.admin_auth.presentation.verify_code.VerifyCodeScreen
import com.edu.wiet_admin.admin_auth.presentation.verify_code.VerifyCodeViewModel
import com.edu.wiet_admin.admin_mgt.navigation.AdminMgtDestination
import com.edu.wiet_admin.navigation.AppDestination

fun NavGraphBuilder.adminAuthNavGraph(
    navController: NavController
) {

    navigation(
        route = AppDestination.AdminAuth.route,
        startDestination = AuthDestination.LoginScreen.route
    ) {

        composable(
            route = AuthDestination.LoginScreen.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        )
        {

            val viewModel = hiltViewModel<LoginViewModel>()
            val state = viewModel.state.collectAsState().value

            LoginScreen(
                onEvent = viewModel::onEvent,
                state = state,
                navigateToHomeScreen = {
                    navController.navigate(AppDestination.Users.route) {
                        popUpTo(AuthDestination.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToForgotPasswordScreen = {
                    navController.navigate(AuthDestination.ForgotPasswordScreen.route)
                }
            )

        }



        composable(
            route = AuthDestination.ForgotPasswordScreen.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        )
        {

            val viewModel = hiltViewModel<ForgotPasswordViewModel>()
            val state = viewModel.state.collectAsState().value

            ForgotPasswordScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navigateToVerifyCodeScreen = {
                    navController.navigate(
                        AuthDestination.VerifyCodeScreen.route
                            .replace(
                                "{email}",
                                state.email
                            )
                    )
                }
            )

        }



        composable(
            route = AuthDestination.VerifyCodeScreen.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            })
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
                    val prevDestination = navController.previousBackStackEntry?.destination?.route

                    if (prevDestination == AdminMgtDestination.AdminDetails.route) {
                        navController.navigate(AdminMgtDestination.AdminDetails.route) {
                            popUpTo(AdminMgtDestination.AdminDetails.route) {
                                inclusive = true
                            }
                        }
                    } else if(prevDestination == AuthDestination.ForgotPasswordScreen.route){
                        navController.navigate(AppDestination.Users.route) {
                            popUpTo(AuthDestination.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }
    }
}