package com.edu.wiet_admin.common.presentation.scaffold.top_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edu.wiet_admin.admin_auth.navigation.AuthDestination
import com.edu.wiet_admin.admin_mgt.navigation.AdminMgtDestination
import com.edu.wiet_admin.navigation.AppDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WietTopAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currDest: String?
) {
    val viewModel = hiltViewModel<TopAppBarViewModel>()
    val state = viewModel.state

    when (currDest) {
        AuthDestination.LoginScreen.route -> {
            viewModel.updateBarVisibility(true)
            viewModel.updateTitle(null)
            viewModel.updateBackButtonVisibility(false)
            viewModel.updateProfileButtonVisibility(false)
        }

        AuthDestination.ForgotPasswordScreen.route -> {
            viewModel.updateBarVisibility(true)
            viewModel.updateTitle("Forgot Password")
            viewModel.updateBackButtonVisibility(true)
            viewModel.updateProfileButtonVisibility(false)
        }

        AuthDestination.VerifyCodeScreen.route -> {
            viewModel.updateBarVisibility(true)
            viewModel.updateTitle("Verify Code")
            viewModel.updateBackButtonVisibility(true)
            viewModel.updateProfileButtonVisibility(false)
        }

        AppDestination.HomeScreen.route -> {
            viewModel.updateBarVisibility(true)
            viewModel.updateTitle("Home")
            viewModel.updateBackButtonVisibility(false)
            viewModel.updateProfileButtonVisibility(true)
        }

        AdminMgtDestination.AdminDetails.route -> {
            viewModel.updateBarVisibility(true)
            viewModel.updateTitle("Admin Details")
            viewModel.updateBackButtonVisibility(true)
            viewModel.updateProfileButtonVisibility(false)
        }

        AdminMgtDestination.UpdatePassword.route -> {
            viewModel.updateBarVisibility(true)
            viewModel.updateTitle("Update password")
            viewModel.updateBackButtonVisibility(true)
            viewModel.updateProfileButtonVisibility(false)
        }

        AdminMgtDestination.AddAdmin.route -> {
            viewModel.updateBarVisibility(true)
            viewModel.updateTitle("Add Admin")
            viewModel.updateBackButtonVisibility(true)
            viewModel.updateProfileButtonVisibility(false)
        }

        else -> {
            viewModel.updateBarVisibility(false)
        }
    }

    if (state.isBarVisible) {
        TopAppBar(
            title = {
                state.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            },
            modifier = modifier,
            navigationIcon = {
                if (state.isBackButtonVisible) {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back Button"
                        )
                    }
                }
            },
            actions = {
                if (state.isProfileButtonVisible) {
                    IconButton(
                        onClick = {
                            navController.navigate(route = AppDestination.AdminMgt.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Account Details"
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MaterialTheme.colorScheme.surface,
                scrolledContainerColor = MaterialTheme.colorScheme.surface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            ),
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
                canScroll = {
                    false
                }
            ),
        )
    }
}


