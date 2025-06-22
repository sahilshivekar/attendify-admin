package com.attendify_admin.feature_admin_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.feature_admin_auth.domain.use_case.LoginUseCase
import com.attendify_admin.feature_admin_auth.domain.use_case.SaveAccessTokenUseCase
import com.attendify_admin.feature_admin_auth.domain.use_case.SaveRefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,

    ) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailOrUsernameChanged -> {
                _state.update { it.copy(emailOrUsername = event.email) }
            }

            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }

            is LoginEvent.PasswordVisibilityChanged -> {
                _state.update { it.copy(isPasswordVisible = event.isVisible) }
            }

            is LoginEvent.LoginClicked -> {
                login(
                    emailOrUsername = _state.value.emailOrUsername,
                    password = _state.value.password
                )
            }
        }
    }

    private fun login(emailOrUsername: String, password: String) {
        _state.update {
            it.copy(
                emailOrUsernameError = null,
                passwordError = null,
            )
        }

        if (emailOrUsername.isBlank()) {
            _state.update {
                it.copy(
                    emailOrUsernameError = "Enter email or username",
                )
            }
            return
        }

        if (password.isBlank()) {
            _state.update {
                it.copy(
                    passwordError = "Password cannot be empty",
                )
            }
            return
        }

        loginUseCase(emailOrUsername, password).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            isPasswordVisible = false
                        )
                    }
                }

                is Resource.Success -> {
                    viewModelScope.launch {
                        result.data?.let {
                            saveAccessTokenUseCase(it.accessToken)
                            saveRefreshTokenUseCase(it.refreshToken)
                        }
                    }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isLoginSuccessful = true
                        )
                    }
                    SnackbarController.sendEvent(SnackbarEvent("Logged in successfully."))

                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    result.message?.let { resultMessage ->
                        SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
