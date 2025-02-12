package com.edu.wiet_admin.admin_auth.presentation.verify_code

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
fun VerifyCodeScreen(
    modifier: Modifier = Modifier,
    state: VerifyCodeState,
    onEvent: (VerifyCodeEvent) -> Unit,
    navigateToHomeOrAdminDetailsScreen: () -> Unit
) {

    if (state.isVerified) {
        navigateToHomeOrAdminDetailsScreen()
    }


    state.isOtherError?.let {
        WietAlertDialog(
            dialogText = it,
            onDismiss = { onEvent(VerifyCodeEvent.DismissAlertDialog) },
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
                text = "Enter the six digit verification code sent to your email address ${state.email}" +
                        "(Code will be invalid after 5 minutes)",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
            WietTextField(
                value = state.code,
                onValueChange = { updatedCode ->
                    onEvent(VerifyCodeEvent.CodeChanged(updatedCode))
                },
                label = "Verification code",
                isError = state.codeError != null,
                supportingText = state.codeError,
                enabled = !state.isVerified && !state.isLoading,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        WietButton(
            onClick = { onEvent(VerifyCodeEvent.VerifyCodeClicked) },
            enabled = !state.isVerified && !state.isLoading,
            isLoading = state.isLoading,
            text = "Verify Code"
        )
    }
}
