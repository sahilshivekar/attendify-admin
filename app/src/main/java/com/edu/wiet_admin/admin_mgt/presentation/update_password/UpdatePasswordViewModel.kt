package com.edu.wiet_admin.admin_mgt.presentation.update_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.wiet_admin.admin_mgt.domain.use_case.UpdateAdminPasswordUseCase
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.validation.Validators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UpdatePasswordViewModel @Inject constructor(
    private val updateAdminPasswordUseCase: UpdateAdminPasswordUseCase
) : ViewModel() {

    var state = MutableStateFlow(UpdatePasswordState())
        private set

    fun onEvent(event: UpdatePasswordEvent) {
        when (event) {
            is UpdatePasswordEvent.ConfirmPasswordChanged -> {
                state.value = state.value.copy(confirmPassword = event.confirmPassword)
            }

            is UpdatePasswordEvent.PasswordChanged -> {
                state.value = state.value.copy(password = event.password)
            }

            is UpdatePasswordEvent.PasswordVisibilityChanged -> {
                state.value = state.value.copy(isPasswordVisible = event.isVisible)
            }

            UpdatePasswordEvent.DismissAlertDialog -> {
                state.value = state.value.copy(alertMessage = null)
            }

            UpdatePasswordEvent.UpdatePasswordClicked -> {
                updatePassword()
            }
        }
    }

    private fun updatePassword() {

        val passwordValidationError = Validators.validatePassword(password = state.value.password)


        if (passwordValidationError != null) {
            state.value = state.value.copy(
                passwordError = passwordValidationError,
                isPasswordVisible = true
            )
            return
        }

        if (state.value.password != state.value.confirmPassword) {
            state.value = state.value.copy(
                confirmPasswordError = "Password and confirm password are not same",
                passwordError = null,
                isPasswordVisible = true
            )
            return
        }
        updateAdminPasswordUseCase(
            password = state.value.password, confirmPassword = state.value.confirmPassword
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isUpdating = true,
                        passwordError = null,
                        confirmPasswordError = null,
                        isPasswordVisible = false
                    )
                }

                is Resource.Error -> {
                    state.value = state.value.copy(
                        passwordError = null,
                        confirmPasswordError = null,
                        isUpdating = false,
                        isPasswordVisible = true
                    )
                    result.message?.let { resultMessage ->
                        if (resultMessage.contains("password", ignoreCase = true)) {
                            state.value = state.value.copy(passwordError = resultMessage)
                        } else {
                            state.value = state.value.copy(alertMessage = resultMessage)
                        }
                    }
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isUpdating = false,
                        isUpdated = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}