package com.attendify_admin.feature_admin_auth.presentation.verify_code

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.common.presentation.components.top_bar.AttendifyTopAppBar
import com.attendify_admin.common.presentation.components.top_bar.TopAppBarState
import com.attendify_admin.ui.theme.AttendifyAdminTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyCodeScreen(
    modifier: Modifier = Modifier,
    state: VerifyCodeState,
    onEvent: (VerifyCodeEvent) -> Unit,
    navigateToHomeOrAdminDetailsScreen: () -> Unit,
    onBackIconButtonClick: () -> Unit,
) {

    LaunchedEffect(state.isVerified) {
        if (state.isVerified) {
            navigateToHomeOrAdminDetailsScreen()
        }
    }

    Scaffold(
        topBar = {
            AttendifyTopAppBar(
                topAppBarState = TopAppBarState(
                    title = "Verify Code",
                    isTopAppBarVisible = true,
                    isBackIconButtonVisible = true,
                    isProfileIconButtonVisible = false
                ),
                onBackIconButtonClick = onBackIconButtonClick,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .widthIn(max = UiConstants.MAX_WIDTH),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Enter the six digit verification code sent to your email address ${state.email}" +
                            "\n\n" +
                            "(Code will be invalid after 5 minutes)",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))
                AttendifyTextField(
                    value = state.code,
                    onValueChange = { updatedCode ->
                        onEvent(VerifyCodeEvent.CodeChanged(updatedCode))
                    },
                    label = "Verification code",
                    isError = state.codeError != null,
                    supportingText = state.codeError,
                    enabled = !state.isVerified && !state.isLoading,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = { onEvent(VerifyCodeEvent.VerifyCodeClicked) })
                )
            }

            AttendifyButton(
                onClick = { onEvent(VerifyCodeEvent.VerifyCodeClicked) },
                enabled = !state.isVerified && !state.isLoading,
                isLoading = state.isLoading,
                text = "Verify Code"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun VerifyCodeScreenPreview() {
    AttendifyAdminTheme {
        VerifyCodeScreen(
            state = VerifyCodeState(email = "test@example.com"),
            onEvent = {},
            navigateToHomeOrAdminDetailsScreen = {},
            onBackIconButtonClick = {}
        )
    }
}
