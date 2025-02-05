package com.edu.wiet_admin.admin_auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.wiet_admin.admin_auth.domain.use_case.LoginUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.ReadAccessTokenUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.SaveAccessTokenUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.SaveRefreshTokenUseCase
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.domain.RemoteUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase
) : ViewModel() {
    var state = MutableStateFlow(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailOrUsernameChanged -> {
                state.value = state.value.copy(emailOrUsername = event.email)
            }

            is LoginEvent.PasswordChanged -> {
                state.value = state.value.copy(password = event.password)
            }

            is LoginEvent.PasswordVisibilityChanged -> {
                state.value = state.value.copy(isPasswordVisible = event.isVisible)
            }

            is LoginEvent.LoginClicked -> {
                login()
            }

            is LoginEvent.DismissAlertDialog -> {
                state.value = state.value.copy(isOtherError = null)
            }
        }
    }

    private fun login() {

        if (state.value.emailOrUsername.isBlank()) {
            state.value = state.value.copy(
                emailOrUsernameError = "Enter email or username",
                passwordError = null
            )
            return
        }

        if (state.value.password.isBlank()) {
            state.value = state.value.copy(
                passwordError = "Password cannot be empty",
                emailOrUsernameError = null,
            )
            return
        }

        loginUseCase(state.value.emailOrUsername, state.value.password).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isEmailOrUsernameEnabled = false,
                        isPasswordEnabled = false,
                        isLoading = true,
                        isLoginButtonEnabled = false,
                        isForgottenPasswordEnabled = false,
                        emailOrUsernameError = null,
                        passwordError = null
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isLoginButtonEnabled = false,
                        isLoginSuccessful = true,
                        isForgottenPasswordEnabled = false,
                        emailOrUsernameError = null,
                        passwordError = null
                    )
                    viewModelScope.launch {
                        result.data?.data?.let {
                            saveAccessTokenUseCase(it.accessToken)
                            saveRefreshTokenUseCase(it.refreshToken)
                        }
                    }

                    Log.d("LoginScreenModel", "login Success")
                }

                is Resource.Error -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isEmailOrUsernameEnabled = true,
                        isPasswordEnabled = true,
                        isLoginButtonEnabled = true,
                        isForgottenPasswordEnabled = true
                    )

                    if (
                        result.message?.contains("email", ignoreCase = true) == true ||
                        result.message?.contains("username", ignoreCase = true) == true ||
                        result.message?.contains("credentials", ignoreCase = true) == true
                    ) {
                        state.value = state.value.copy(
                            emailOrUsernameError = result.message,
                            passwordError = null
                        )
                    } else if (result.message?.contains("password", ignoreCase = true) == true) {
                        state.value = state.value.copy(
                            passwordError = result.message,
                            emailOrUsernameError = null,
                        )
                    } else if(result.message == RemoteUtils.NETWORK_IO){
                        state.value = state.value.copy(isOtherError = RemoteUtils.NETWORK_IO)
                    } else {
                        state.value = state.value.copy(isOtherError = RemoteUtils.UNKNOWN_NETWORK_ERROR)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}