package com.presencify_admin.feature_admin_mgt.presentation.update_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.presencify_admin.R
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyTextField
import com.presencify_admin.common.presentation.components.top_bar.PresencifyTopAppBar
import com.presencify_admin.common.presentation.components.top_bar.TopAppBarState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePasswordScreen(
    modifier: Modifier = Modifier,
    state: UpdatePasswordState,
    onEvent: (UpdatePasswordEvent) -> Unit,
    navigateToAdminDetailsScreen: () -> Unit,
    onBackIconButtonClick: () -> Unit,
) {
    LaunchedEffect(state.isUpdated) {
        if (state.isUpdated) {
            navigateToAdminDetailsScreen()
        }
    }

    val confirmPasswordFocusRequester = remember { FocusRequester() }
    Scaffold(
        topBar = {
            PresencifyTopAppBar(
                topAppBarState = TopAppBarState(
                    title = "Update Password",
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
                .fillMaxSize()
                .widthIn(max = UiConstants.MAX_WIDTH)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Enter your new password and confirm it.",
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))
                PresencifyTextField(
                    value = state.password,
                    onValueChange = { updatedPassword ->
                        onEvent(UpdatePasswordEvent.PasswordChanged(updatedPassword))
                    },
                    label = "Password",
                    isError = state.passwordError != null,
                    supportingText = state.passwordError,
                    enabled = !state.isUpdating && !state.isUpdated,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(imeAction = Next),
                    keyboardActions = KeyboardActions(onNext = { confirmPasswordFocusRequester.requestFocus() }),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onEvent(UpdatePasswordEvent.PasswordVisibilityChanged(isVisible = !state.isPasswordVisible))
                            },
                            enabled = !state.isUpdating
                        ) {
                            Icon(
                                painter = if (state.isPasswordVisible) painterResource(R.drawable.baseline_visibility_24) else painterResource(
                                    R.drawable.baseline_visibility_off_24
                                ),
                                contentDescription = null,
                                tint = colorResource(R.color.text_field_border_label)
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                PresencifyTextField(
                    value = state.confirmPassword,
                    onValueChange = { confirmPassword ->
                        onEvent(UpdatePasswordEvent.ConfirmPasswordChanged(confirmPassword))
                    },
                    label = "Confirm password",
                    enabled = !state.isUpdating && !state.isUpdated,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .widthIn(max = UiConstants.MAX_WIDTH)
                        .fillMaxWidth()
                        .focusRequester(confirmPasswordFocusRequester),
                    keyboardOptions = KeyboardOptions(imeAction = Done),
                    keyboardActions = KeyboardActions(onDone = { onEvent(UpdatePasswordEvent.UpdatePasswordClicked) })
                )
            }

            PresencifyButton(
                onClick = { onEvent(UpdatePasswordEvent.UpdatePasswordClicked) },
                enabled = !state.isUpdating && !state.isUpdated,
                isLoading = state.isUpdating,
                text = "Update password"
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun UpdatePasswordScreenPreview() {
    PreviewWrapper {
        UpdatePasswordScreen(
            state = UpdatePasswordState(),
            onEvent = {},
            navigateToAdminDetailsScreen = {},
            onBackIconButtonClick = {}
        )
    }
}



