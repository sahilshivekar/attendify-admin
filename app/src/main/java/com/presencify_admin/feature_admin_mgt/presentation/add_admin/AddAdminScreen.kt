package com.presencify_admin.feature_admin_mgt.presentation.add_admin

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
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
fun AddAdminScreen(
    modifier: Modifier = Modifier,
    state: AddAdminState,
    onEvent: (AddAdminEvent) -> Unit,
    navigateUp: () -> Unit,
) {

    LaunchedEffect(state.isAdded) {
        if (state.isAdded) navigateUp()

    }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            PresencifyTopAppBar(
                topAppBarState = TopAppBarState(
                    title = "Add Admin",
                    isTopAppBarVisible = true,
                    isBackIconButtonVisible = true,
                    isProfileIconButtonVisible = false
                ),
                onBackIconButtonClick = navigateUp
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.widthIn(max = UiConstants.MAX_WIDTH)
            ) {

                Text(
                    text = "Fill in necessary details to add another admin",
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = UiConstants.MAX_WIDTH)
                        .align(Alignment.Start),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))
                PresencifyTextField(
                    value = state.username,
                    onValueChange = { onEvent(AddAdminEvent.UsernameChanged(it)) },
                    isError = state.usernameError != null,
                    supportingText = state.usernameError,
                    label = "Username",
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            emailFocusRequester.requestFocus()
                        }
                    ),
                    enabled = !state.isAdding
                )

                PresencifyTextField(
                    value = state.email,
                    onValueChange = { onEvent(AddAdminEvent.EmailChanged(it)) },
                    isError = state.emailError != null,
                    supportingText = state.emailError,
                    label = "Email",
                    enabled = !state.isAdding,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            passwordFocusRequester.requestFocus()
                        }
                    ),
                    modifier = Modifier
                        .widthIn(max = UiConstants.MAX_WIDTH)
                        .fillMaxWidth()
                        .focusRequester(emailFocusRequester)
                )

                PresencifyTextField(
                    value = state.password,
                    modifier = Modifier
                        .widthIn(max = UiConstants.MAX_WIDTH)
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            confirmPasswordFocusRequester.requestFocus()
                        }
                    )
                )

                PresencifyTextField(
                    value = state.confirmPassword,
                    modifier = Modifier
                        .widthIn(max = UiConstants.MAX_WIDTH)
                        .fillMaxWidth()
                        .focusRequester(confirmPasswordFocusRequester),
                    onValueChange = { confirmPassword ->
                        onEvent(AddAdminEvent.ConfirmPasswordChanged(confirmPassword))
                    },
                    label = "Confirm password",
                    isError = state.confirmPasswordError != null,
                    supportingText = state.confirmPasswordError,
                    enabled = !state.isAdding && !state.isAdded,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onEvent(AddAdminEvent.AddAdminClicked) }
                    )
                )
            }
            PresencifyButton(
                onClick = { onEvent(AddAdminEvent.AddAdminClicked) },
                enabled = !state.isAdding && !state.isAdded,
                isLoading = state.isAdding,
                text = "Add admin"
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun AddAdminScreenPreview() {
    PreviewWrapper {
        AddAdminScreen(
            state = AddAdminState(),
            onEvent = {},
            navigateUp = {}
        )
    }
}