package com.presencify_admin.feature_admin_auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.presencify_admin.R
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyTextButton
import com.presencify_admin.common.presentation.components.PresencifyTextField

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

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .widthIn(max = UiConstants.MAX_WIDTH)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = CenterHorizontally
    ) {
        val passwordFocusRequester = remember { FocusRequester() }
        val presencifyLogoPainter =
            rememberAsyncImagePainter(model = R.drawable.presencify_logo_circle_svg)
        val passwordVisibleIconPainter = rememberAsyncImagePainter(
            model = when (state.isPasswordVisible) {
                true -> R.drawable.baseline_visibility_24
                false -> R.drawable.baseline_visibility_off_24
            }
        )

        Image(
            modifier = Modifier.padding(top = 36.dp).size(80.dp),
            painter = presencifyLogoPainter,
            contentDescription = null,
        )
        Text(
            text = "Presencify",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium),
        )
        Spacer(Modifier.height(60.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.widthIn(max = UiConstants.MAX_WIDTH)
        ) {

            Text(
                text = "Welcome back!",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Text(
                text = "Log in to continue",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
        }

        Spacer(Modifier.height(10.dp))
        PresencifyTextField(
            value = state.emailOrUsername,
            onValueChange = {
                onEvent(LoginEvent.EmailOrUsernameChanged(it))
            },
            label = "Email or Username",
            enabled = !state.isLoading,
            isError = state.emailOrUsernameError != null,
            supportingText = state.emailOrUsernameError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
        )

//        Spacer(Modifier.height(8.dp))
        PresencifyTextField(
            value = state.password,
            onValueChange = {
                onEvent(LoginEvent.PasswordChanged(it))
            },
            label = "Password",
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onEvent(LoginEvent.LoginClicked) }),
            enabled = !state.isLoading,
            isError = state.passwordError != null,
            supportingText = state.passwordError,
            modifier = Modifier
                .focusRequester(passwordFocusRequester)
                .widthIn(max = UiConstants.MAX_WIDTH)
                .fillMaxWidth(),
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
//        Spacer(Modifier.height(8.dp))
        PresencifyButton(
            onClick = { onEvent(LoginEvent.LoginClicked) },
            enabled = !state.isLoading,
            isLoading = state.isLoading,
            text = "Log in"
        )


        Spacer(Modifier.height(16.dp))
        PresencifyTextButton(
            onClick = navigateToForgotPasswordScreen,
            enabled = !state.isLoading
        ) {
            Text("Forgotten Password?")
        }

    }
}


@Preview
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