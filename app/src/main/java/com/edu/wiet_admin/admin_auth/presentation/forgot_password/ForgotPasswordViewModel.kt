package com.edu.wiet_admin.admin_auth.presentation.forgot_password

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.wiet_admin.admin_auth.domain.use_case.ForgotPasswordUseCase
import com.edu.wiet_admin.admin_auth.presentation.login.LoginEvent
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.domain.RemoteUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {
    var state = MutableStateFlow(ForgotPasswordState())
        private set

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.EmailChanged -> {
                state.value = state.value.copy(email = event.email)
            }

            is ForgotPasswordEvent.SendCodeClicked -> {
                if (state.value.email.isBlank()) {
                    state.value = state.value.copy(
                        emailError = "Email can't be blank"
                    )
                    return
                }
                sendCode(state.value.email)
            }

            is ForgotPasswordEvent.DismissAlertDialog -> {
                state.value = state.value.copy(isOtherError = null)
            }
        }
    }

    private fun sendCode(email: String) {
        forgotPasswordUseCase(email).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isLoading = true,
                        emailError = null
                    )
                }

                is Resource.Error -> {

                    when (result.message) {
                        RemoteUtils.NETWORK_IO -> {
                            state.value = state.value.copy(
                                isOtherError = RemoteUtils.NETWORK_IO,
                                isLoading = false
                            )
                        }

                        RemoteUtils.UNKNOWN_NETWORK_ERROR -> {
                            state.value = state.value.copy(
                                isOtherError = RemoteUtils.UNKNOWN_NETWORK_ERROR,
                                isLoading = false
                            )
                        }

                        else -> {
                            result.message?.let { msg ->
                                state.value = state.value.copy(
                                    isLoading = false,
                                    emailError = msg
                                )
                            }
                        }
                    }
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isEmailSent = true
                    )

                    // bcz if the user click back button from verify code he must not be again automatically navigating to the verify code screen again
                    delay(1000L)
                    state.value = state.value.copy(
                        isLoading = false,
                        isEmailSent = false
                    )

                }

            }
        }.launchIn(viewModelScope)
    }
}