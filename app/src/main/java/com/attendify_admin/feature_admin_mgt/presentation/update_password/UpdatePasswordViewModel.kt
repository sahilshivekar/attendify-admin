package com.attendify_admin.feature_admin_mgt.presentation.update_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.common.validation.ValidatePassword
import com.attendify_admin.feature_admin_mgt.domain.use_case.UpdateAdminPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UpdatePasswordViewModel @Inject constructor(
    private val updateAdminPasswordUseCase: UpdateAdminPasswordUseCase,
    private val validatePassword: ValidatePassword,
) : ViewModel() {

    private val _state = MutableStateFlow(UpdatePasswordState())
    val state = _state.asStateFlow()

    fun onEvent(event: UpdatePasswordEvent) {
        when (event) {
            is UpdatePasswordEvent.ConfirmPasswordChanged -> {
                _state.update { it.copy(confirmPassword = event.confirmPassword) }
            }

            is UpdatePasswordEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }

            is UpdatePasswordEvent.PasswordVisibilityChanged -> {
                _state.update { it.copy(isPasswordVisible = event.isVisible) }
            }

            UpdatePasswordEvent.UpdatePasswordClicked -> {
                updatePassword()
            }

        }
    }

    private fun updatePassword() {
        val passwordValidationError = validatePassword(password = _state.value.password)


        _state.update {
            it.copy(
                passwordError = passwordValidationError.errorMessage,
                isPasswordVisible = true
            )
        }

        if (!passwordValidationError.successful) {
            return
        }

        var errorMessage: String? = null

        if (_state.value.password != _state.value.confirmPassword) {
            errorMessage = "Passwords do not match."
        }

        _state.update {
            it.copy(
                passwordError = null,
                isPasswordVisible = errorMessage != null
            )
        }

        if (_state.value.passwordError != null) {
            return
        }

        updateAdminPasswordUseCase(
            password = _state.value.password, confirmPassword = _state.value.confirmPassword
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isUpdating = true,
                            isPasswordVisible = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isUpdating = false,
                            isPasswordVisible = true
                        )
                    }
                    result.message?.let { resultMessage ->
                        SnackbarController.sendEvent(SnackbarEvent(resultMessage))
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isUpdating = false,
                            isUpdated = true
                        )
                    }
                    SnackbarController.sendEvent(SnackbarEvent("Password update successfully."))
                }
            }
        }.launchIn(viewModelScope)
    }

}