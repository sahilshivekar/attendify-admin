package com.presencify_admin.feature_admin_mgt.presentation.add_admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.common.validation.ValidateEmail
import com.presencify_admin.common.validation.ValidatePassword
import com.presencify_admin.common.validation.ValidateUsername
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.AdminRequestBody
import com.presencify_admin.feature_admin_mgt.domain.use_case.AddAdminUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddAdminViewModel @Inject constructor(
    private val addAdminUseCase: AddAdminUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateUsername: ValidateUsername,
) : ViewModel() {

    private val _state = MutableStateFlow(AddAdminState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddAdminEvent) {
        when (event) {
            is AddAdminEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is AddAdminEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }

            is AddAdminEvent.ConfirmPasswordChanged -> {
                _state.update { it.copy(confirmPassword = event.confirmPassword) }
            }

            is AddAdminEvent.UsernameChanged -> {
                _state.update { it.copy(username = event.username) }
            }

            AddAdminEvent.AddAdminClicked -> {
                addAdmin()
            }

            AddAdminEvent.PasswordVisibilityChanged -> {
                _state.update { it.copy(isPasswordVisible = !_state.value.isPasswordVisible) }
            }
        }
    }

    private fun addAdmin() {

        val emailValidationResult = validateEmail(email = state.value.email)
        val usernameValidationResult = validateUsername(username = _state.value.username)
        val passwordValidationResult = validatePassword(password = _state.value.password)

        _state.update {
            it.copy(
                emailError = emailValidationResult.errorMessage,
                usernameError = usernameValidationResult.errorMessage,
                passwordError = passwordValidationResult.errorMessage
            )
        }

        val hasError = listOf(
            emailValidationResult,
            usernameValidationResult,
            passwordValidationResult
        ).any { !it.successful }

        if (hasError) return

        if (_state.value.password != _state.value.confirmPassword) {
            _state.update {
                it.copy(
                    confirmPasswordError = "Password and confirm password are not same",
                    isPasswordVisible = true
                )
                return
            }
        }

        val adminRequestBody = AdminRequestBody(
            email = _state.value.email,
            username = _state.value.username,
            password = _state.value.password
        )

        addAdminUseCase(adminRequestBody).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isAdding = true
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update { it.copy(isAdding = false) }
                    result.message?.let {
                        SnackbarController.sendEvent(SnackbarEvent(result.message))
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isAdding = false,
                            isAdded = true,
                        )
                    }
                    SnackbarController.sendEvent(SnackbarEvent("Admin added successfully."))

                }
            }


        }.launchIn(viewModelScope)
    }
}