package com.edu.wiet_admin.admin_auth.presentation.login

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.R
import com.edu.wiet_admin.common.presentation.PreviewWrapper
import com.edu.wiet_admin.common.presentation.ScreenPreview
import com.edu.wiet_admin.common.presentation.components.WietAlertDialog
import com.edu.wiet_admin.common.presentation.components.WietButton
import com.edu.wiet_admin.common.presentation.components.WietTextButton
import com.edu.wiet_admin.common.presentation.components.WietTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
    state: LoginState,
    navigateToHomeScreen: () -> Unit,
    navigateToForgotPasswordScreen: () -> Unit
) {

    if (state.isLoginSuccessful) {
        navigateToHomeScreen()
    }

    state.isOtherError?.let {
        WietAlertDialog(dialogText = it) {
            onEvent(LoginEvent.DismissAlertDialog)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.wiet_logo_circle_svg),
            contentDescription = null,
        )


        Spacer(Modifier.height(12.dp))
        Text(
            text = "Admin Login",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium)
        )

        Spacer(Modifier.height(80.dp))
        WietTextField(
            value = state.emailOrUsername,
            onValueChange = {
                onEvent(LoginEvent.EmailOrUsernameChanged(it))
            },
            label = "Email or Username",
            enabled = state.isEmailOrUsernameEnabled,
            isError = state.emailOrUsernameError != null,
            supportingText = state.emailOrUsernameError
        )

        Spacer(Modifier.height(8.dp))
        WietTextField(
            value = state.password,
            onValueChange = {
                onEvent(LoginEvent.PasswordChanged(it))
            },
            label = "Password",
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            enabled = state.isPasswordEnabled,
            isError = state.passwordError != null,
            supportingText = state.passwordError,
            trailingIcon = {
                IconButton(
                    onClick = {
                        onEvent(LoginEvent.PasswordVisibilityChanged(isVisible = !state.isPasswordVisible))
                    }
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

        Spacer(Modifier.height(8.dp))
        WietButton(
            onClick = { onEvent(LoginEvent.LoginClicked) },
            enabled = state.isLoginButtonEnabled,
            isLoading = state.isLoading,
            text = "Log in"
        )



        Spacer(Modifier.height(16.dp))
        WietTextButton(
            onClick = { navigateToForgotPasswordScreen() },
            enabled = state.isForgottenPasswordEnabled
        ) {
            Text("Forgotten Password?")
        }
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