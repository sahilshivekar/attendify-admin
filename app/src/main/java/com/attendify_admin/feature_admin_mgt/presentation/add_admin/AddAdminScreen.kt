package com.attendify_admin.feature_admin_mgt.presentation.add_admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.attendify_admin.R
import com.attendify_admin.common.presentation.components.AttendifyAlertDialog
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.common.presentation.components.top_bar.AttendifyTopAppBar
import com.attendify_admin.common.presentation.components.top_bar.TopAppBarState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAdminScreen(
    modifier: Modifier = Modifier,
    state: AddAdminState,
    onEvent: (AddAdminEvent) -> Unit,
    navigateToAdminDetailsScreen: () -> Unit,
    onBackIconButtonClick: () -> Unit
) {

    state.alertMessage?.let {
        AttendifyAlertDialog(
            dialogText = it,
            onDismiss = { onEvent(AddAdminEvent.DismissAlertDialog) }
        )
    }

    if (state.isAdded) {
        navigateToAdminDetailsScreen()
    }

    Scaffold(
        topBar = {
            AttendifyTopAppBar(
                topAppBarState = TopAppBarState(
                    title = "Add Admin",
                    isTopAppBarVisible = true,
                    isBackIconButtonVisible = true,
                    isProfileIconButtonVisible = false
                ),
                onBackIconButtonClick = onBackIconButtonClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Add necessary details to add another admin",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))
                AttendifyTextField(
                    value = state.username,
                    onValueChange = { onEvent(AddAdminEvent.UsernameChanged(it)) },
                    isError = state.usernameError != null,
                    supportingText = state.usernameError,
                    label = "Username",
                    enabled = !state.isAdding
                )

                Spacer(modifier = Modifier.height(8.dp))
                AttendifyTextField(
                    value = state.email,
                    onValueChange = { onEvent(AddAdminEvent.EmailChanged(it)) },
                    isError = state.emailError != null,
                    supportingText = state.emailError,
                    label = "Email",
                    enabled = !state.isAdding,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(8.dp))
                AttendifyTextField(
                    value = state.password,
                    onValueChange = { updatedPassword ->
                        onEvent(AddAdminEvent.PasswordChanged(updatedPassword))
                    },
                    label = "Password",
                    isError = state.passwordError != null,
                    supportingText = state.passwordError,
                    enabled = !state.isAdding && !state.isAdded,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onEvent(AddAdminEvent.PasswordVisibilityChanged)
                            },
                            enabled = !state.isAdding
                        ) {
                            Icon(
                                painter = if (state.isPasswordVisible) painterResource(R.drawable.baseline_visibility_24) else painterResource(
                                    R.drawable.baseline_visibility_off_24
                                ),
                                contentDescription = null,
                                tint = colorResource(R.color.text_field_border_label)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(8.dp))
                AttendifyTextField(
                    value = state.confirmPassword,
                    onValueChange = { confirmPassword ->
                        onEvent(AddAdminEvent.ConfirmPasswordChanged(confirmPassword))
                    },
                    label = "Confirm password",
                    isError = state.confirmPasswordError != null,
                    supportingText = state.confirmPasswordError,
                    enabled = !state.isAdding && !state.isAdded,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            AttendifyButton(
                onClick = { onEvent(AddAdminEvent.AddAdminClicked) },
                enabled = !state.isAdding && !state.isAdded,
                isLoading = state.isAdding,
                text = "Add admin"
            )
        }
    }
}
