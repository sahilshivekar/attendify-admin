package com.attendify_admin.feature_admin_auth.presentation.forgot_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.feature_admin_auth.domain.use_case.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordState())
    var state = _state.asStateFlow()


    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is ForgotPasswordEvent.SendCodeClicked -> {
                if (_state.value.email.isBlank()) {
                    _state.update { it.copy(
                        emailError = "Email can't be blank"
                    ) }
                    return
                }
                sendCode(_state.value.email)
            }

            is ForgotPasswordEvent.DismissAlertDialog -> {
                _state.update { it.copy(isOtherError = null) }
            }
        }
    }

    private fun sendCode(email: String) {
        forgotPasswordUseCase(email).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update { it.copy(
                        isLoading = true,
                        emailError = null
                    ) }
                }

                is Resource.Error -> {

                    when (result.message) {
                        RemoteUtils.NETWORK_IO_ERROR_MESSAGE -> {
                            _state.update { it.copy(
                                isOtherError = RemoteUtils.NETWORK_IO_ERROR_MESSAGE,
                                isLoading = false
                            ) }
                        }

                        RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE -> {
                            _state.update { it.copy(
                                isOtherError = RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE,
                                isLoading = false
                            ) }
                        }

                        else -> {
                            result.message?.let { msg ->
                                _state.update { it.copy(
                                    isLoading = false,
                                    emailError = msg
                                ) }
                            }
                        }
                    }
                }

                is Resource.Success -> {
                    _state.update { it.copy(
                        isLoading = false,
                        isEmailSent = true
                    ) }
                }

            }
        }.launchIn(viewModelScope)
    }
}