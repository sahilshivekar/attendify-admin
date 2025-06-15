package com.attendify_admin.feature_admin_auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.attendify_admin.R
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.ScreenPreview
import com.attendify_admin.common.presentation.components.AttendifyAlertDialog
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextButton
import com.attendify_admin.common.presentation.components.AttendifyTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
    state: LoginState,
    navigateToHomeScreen: () -> Unit,
    navigateToForgotPasswordScreen: () -> Unit,
) {

    LaunchedEffect(state.isLoginSuccessful) {
        if (state.isLoginSuccessful) {
            navigateToHomeScreen()
        }
    }

    state.isOtherError?.let {
        AttendifyAlertDialog(
            dialogText = it,
            onDismiss = { onEvent(LoginEvent.DismissAlertDialog) },
        )
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val attendifyLogoPainter =
            rememberAsyncImagePainter(model = R.drawable.attendify_logo_circle_svg)
        val passwordVisibleIconPainter = rememberAsyncImagePainter(
            model = when (state.isPasswordVisible) {
                true -> R.drawable.baseline_visibility_24
                false -> R.drawable.baseline_visibility_off_24
            }
        )
        Image(
            modifier = Modifier.size(80.dp),
            painter = attendifyLogoPainter,
            contentDescription = null,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Admin Logins",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium)
        )

        
        Spacer(Modifier.height(80.dp))
        AttendifyTextField(
            value = state.emailOrUsername,
            onValueChange = {
                onEvent(LoginEvent.EmailOrUsernameChanged(it))
            },
            label = "Email or Username",
            enabled = !state.isLoading,
            isError = state.emailOrUsernameError != null,
            supportingText = state.emailOrUsernameError
        )

        Spacer(Modifier.height(8.dp))
        AttendifyTextField(
            value = state.password,
            onValueChange = {
                onEvent(LoginEvent.PasswordChanged(it))
            },
            label = "Password",
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            enabled = !state.isLoading,
            isError = state.passwordError != null,
            supportingText = state.passwordError,
            trailingIcon = {
                val isPasswordVisible = state.isPasswordVisible

                IconButton(
                    onClick = {
                        onEvent(LoginEvent.PasswordVisibilityChanged(isVisible = !isPasswordVisible))
                    },
                    enabled = !state.isLoading
                ) {
                    Icon(
                        painter = passwordVisibleIconPainter,
                        contentDescription = null,
                        tint = colorResource(R.color.text_field_border_label)
                    )
                }
            },
        )
        Spacer(Modifier.height(8.dp))
        AttendifyButton(
            onClick = { onEvent(LoginEvent.LoginClicked) },
            enabled = state.isLoginButtonEnabled,
            isLoading = state.isLoading,
            text = "Log in"
        )


        Spacer(Modifier.height(16.dp))
        AttendifyTextButton(
            onClick = { navigateToForgotPasswordScreen() },
            enabled = state.isForgottenPasswordEnabled
        ) {
            Text("Forgotten Password?")
        }
        // to give space from bottom so that the text-fields come at center of screen
        Spacer(Modifier.height(130.dp))
    }
}


@ScreenPreview
@Composable
fun LoginScreenPreview() {
    PreviewWrapper {
        LoginScreen(
            onEvent = {},
            state = LoginState(),
            navigateToHomeScreen = {},
            navigateToForgotPasswordScreen = {})
    }
}