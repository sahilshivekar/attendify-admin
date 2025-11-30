package com.presencify_admin.feature_admin_auth.presentation.forgot_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.common.validation.ValidateEmail
import com.presencify_admin.feature_admin_auth.domain.use_case.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val validateEmail: ValidateEmail,
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordState())
    var state = _state.asStateFlow()


    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is ForgotPasswordEvent.SendCodeClicked -> {
                sendCode(_state.value.email)
            }


        }
    }

    private fun sendCode(email: String) {
        val emailValidationResult = validateEmail(_state.value.email)
        _state.update { it.copy(emailError = emailValidationResult.errorMessage) }
        if (!emailValidationResult.successful) {
            return
        }
        forgotPasswordUseCase(email).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            emailError = null
                        )
                    }
                }

                is Resource.Error -> {
                    result.message?.let { resultMessage ->
                        _state.update { it.copy(isLoading = false) }
                        SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isEmailSent = true
                        )
                    }
                    SnackbarController.sendEvent(SnackbarEvent("Email sent on ${_state.value.email}."))


                    // if user clicks go back then it will navigate again to verify code again if isEmailSent is true
                    delay(1000L)
                    _state.update {
                        it.copy(
                            isEmailSent = false
                        )
                    }
                }

            }
        }.launchIn(viewModelScope)
    }
}