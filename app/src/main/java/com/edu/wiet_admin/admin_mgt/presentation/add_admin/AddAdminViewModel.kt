package com.edu.wiet_admin.admin_mgt.presentation.add_admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.wiet_admin.admin_mgt.data.remote.AdminRequestBody
import com.edu.wiet_admin.admin_mgt.domain.use_case.AddAdminUseCase
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.validation.Validators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddAdminViewModel @Inject constructor(
    private val addAdminUseCase: AddAdminUseCase
) : ViewModel() {

    var state = MutableStateFlow(AddAdminState())
        private set

    fun onEvent(event: AddAdminEvent) {
        when (event) {
            is AddAdminEvent.EmailChanged -> {
                state.value = state.value.copy(email = event.email)
            }

            is AddAdminEvent.PasswordChanged -> {
                state.value = state.value.copy(password = event.password)
            }

            is AddAdminEvent.ConfirmPasswordChanged -> {
                state.value = state.value.copy(confirmPassword = event.confirmPassword)
            }

            is AddAdminEvent.UsernameChanged -> {
                state.value = state.value.copy(username = event.username)
            }

            AddAdminEvent.AddAdminClicked -> {
                addAdmin()
            }

            AddAdminEvent.DismissAlertDialog -> {
                state.value = state.value.copy(alertMessage = null)
            }

            AddAdminEvent.PasswordVisibilityChanged -> {
                state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }
        }
    }

    private fun addAdmin() {

        val emailValidationError = Validators.validateEmail(email = state.value.email)
        val usernameValidationError = Validators.validateUsername(username = state.value.username)
        val passwordValidationError = Validators.validatePassword(password = state.value.password)

        state.value = state.value.copy(
            emailError = emailValidationError,
            usernameError = usernameValidationError,
            passwordError = passwordValidationError
        )

        if(passwordValidationError != null) {
            state.value = state.value.copy(isPasswordVisible = true)
        }

        if (
            emailValidationError != null ||
            usernameValidationError != null ||
            passwordValidationError != null
        ) return

        if (state.value.password != state.value.confirmPassword) {
            Log.d("Password", state.value.password)
            Log.d("confirm Password", state.value.confirmPassword)

            state.value = state.value.copy(
                confirmPasswordError = "Password and confirm password are not same",
                passwordError = null,
                isPasswordVisible = true
            )
            return
        }

        val adminRequestBody = AdminRequestBody(
            email = state.value.email,
            username = state.value.username,
            password = state.value.password
        )
        addAdminUseCase(adminRequestBody).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isAdding = true,
                        emailError = null,
                        usernameError = null,
                        passwordError = null,
                        confirmPasswordError = null,
                        isPasswordVisible = false
                    )
                }

                is Resource.Error -> {
                    state.value = state.value.copy(
                        isAdding = false,
                    )
                    result.message?.let { resultMessage ->
                        if (resultMessage.contains("email", ignoreCase = true)) {
                            state.value = state.value.copy(emailError = resultMessage)
                        } else if (resultMessage.contains("username", ignoreCase = true)) {
                            state.value = state.value.copy(usernameError = resultMessage)
                        } else if (resultMessage.contains("password", ignoreCase = true)) {
                            state.value = state.value.copy(passwordError = resultMessage, isPasswordVisible = true)
                        } else {
                            state.value = state.value.copy(alertMessage = resultMessage)
                        }
                    }
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isAdding = false,
                        isAdded = true,
                    )
                }
            }


        }.launchIn(viewModelScope)
    }
}