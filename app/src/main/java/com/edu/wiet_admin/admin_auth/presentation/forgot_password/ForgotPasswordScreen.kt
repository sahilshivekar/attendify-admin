package com.edu.wiet_admin.admin_auth.presentation.forgot_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.common.presentation.components.WietAlertDialog
import com.edu.wiet_admin.common.presentation.components.WietButton
import com.edu.wiet_admin.common.presentation.components.WietTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    state: ForgotPasswordState,
    onEvent: (ForgotPasswordEvent) -> Unit,
    navigateToVerifyCodeScreen: () -> Unit
) {
    if (state.isEmailSent) {
        navigateToVerifyCodeScreen()
    }

    state.isOtherError?.let {
        WietAlertDialog(
            dialogText = it,
            onDismiss = { onEvent(ForgotPasswordEvent.DismissAlertDialog) }
        )
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Enter your email address and we will send you a verification code.",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
            WietTextField(
                value = state.email,
                onValueChange = { updatedEmail ->
                    onEvent(ForgotPasswordEvent.EmailChanged(updatedEmail))
                },
                label = "Email",
                isError = state.emailError != null,
                supportingText = state.emailError,
                enabled = !state.isEmailSent && !state.isLoading,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

            )
        }

        WietButton(
            onClick = { onEvent(ForgotPasswordEvent.SendCodeClicked) },
            enabled = !state.isEmailSent && !state.isLoading,
            isLoading = state.isLoading,
            text = "Send Code"
        )
    }
}
