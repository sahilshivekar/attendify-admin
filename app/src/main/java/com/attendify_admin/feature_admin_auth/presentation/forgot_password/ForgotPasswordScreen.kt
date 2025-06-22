package com.attendify_admin.feature_admin_auth.presentation.forgot_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants.MAX_WIDTH
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.common.presentation.components.top_bar.AttendifyTopAppBar
import com.attendify_admin.common.presentation.components.top_bar.TopAppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    state: ForgotPasswordState,
    onEvent: (ForgotPasswordEvent) -> Unit,
    navigateToVerifyCodeScreen: () -> Unit,
    onBackIconButtonClick: () -> Unit,
) {
    LaunchedEffect(state.isEmailSent) {
        if (state.isEmailSent) {
            navigateToVerifyCodeScreen()
        }
    }

    Scaffold(
        topBar = {
            AttendifyTopAppBar(
                topAppBarState = TopAppBarState(
                    title = "Forgot Password",
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Text(
                    text = "Enter your email address and we will send you a verification code.",
                    modifier = Modifier
                        .widthIn(max = MAX_WIDTH),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))
                AttendifyTextField(
                    value = state.email,
                    onValueChange = { updatedEmail ->
                        onEvent(ForgotPasswordEvent.EmailChanged(updatedEmail))
                    },
                    label = "Email",
                    isError = state.emailError != null,
                    supportingText = state.emailError,
                    enabled = !state.isEmailSent && !state.isLoading,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = { onEvent(ForgotPasswordEvent.EmailChanged(state.email)) })

                )
            }

            AttendifyButton(
                onClick = { onEvent(ForgotPasswordEvent.SendCodeClicked) },
                enabled = !state.isEmailSent && !state.isLoading,
                isLoading = state.isLoading,
                text = "Send Code"
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ForgotPasswordScreenPreview() {
    PreviewWrapper {
        ForgotPasswordScreen(
            state = ForgotPasswordState(),
            onEvent = {},
            navigateToVerifyCodeScreen = { },
            onBackIconButtonClick = { }
        )
    }
}
