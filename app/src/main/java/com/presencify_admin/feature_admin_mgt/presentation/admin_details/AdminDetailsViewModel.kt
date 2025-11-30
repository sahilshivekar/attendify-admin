package com.presencify_admin.feature_admin_mgt.presentation.admin_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarAction
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.common.validation.Validators
import com.presencify_admin.feature_admin_auth.domain.use_case.LogoutUseCase
import com.presencify_admin.feature_admin_auth.domain.use_case.RemoveAuthTokensUseCase
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdateAdminDetailsRequestBody
import com.presencify_admin.feature_admin_mgt.domain.use_case.GetAdminDetailsUseCase
import com.presencify_admin.feature_admin_mgt.domain.use_case.RemoveAdminUseCase
import com.presencify_admin.feature_admin_mgt.domain.use_case.SendVerificationCodeToEmailUseCase
import com.presencify_admin.feature_admin_mgt.domain.use_case.UpdateAdminDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminDetailsViewModel @Inject constructor(
    private val getAdminDetailsUseCase: GetAdminDetailsUseCase,
    private val removeAuthTokensUseCase: RemoveAuthTokensUseCase,
    private val removeAdminUseCase: RemoveAdminUseCase,
    private val sendVerificationCodeToEmailUseCase: SendVerificationCodeToEmailUseCase,
    private val updateAdminDetailsUseCase: UpdateAdminDetailsUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AdminDetailsState())
    val state = _state.asStateFlow()

    init {
        getAdminDetails()
    }

    fun onEvent(event: AdminDetailsEvent) {
        when (event) {
            AdminDetailsEvent.CancelEditingDetailsClicked -> {
                _state.update {
                    it.copy(
                        isEditingDetails = false,
                        isUsernameEmailEnabled = false,
                        editableEmail = _state.value.orgEmail,
                        editableUsername = _state.value.orgUsername,
                        emailError = null,
                        usernameError = null
                    )
                }
            }


            AdminDetailsEvent.EditDetailsClicked -> {
                _state.update {
                    it.copy(
                        isEditingDetails = true,
                        isUsernameEmailEnabled = true
                    )
                }

            }

            AdminDetailsEvent.LogoutClicked -> {
                logoutAdmin()
            }

            AdminDetailsEvent.DismissRemoveAccountClicked -> {
                _state.update {
                    it.copy(
                        showRemoveAccountConfirmationDialog = false
                    )
                }
            }

            AdminDetailsEvent.RemoveAdminClicked -> {
                viewModelScope.launch {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Are you sure you want to remove your account?",
                            action = SnackbarAction(
                                name = "Remove",
                                action = {
                                    removeAdmin()
                                },
                            )
                        )
                    )
                }
            }

            AdminDetailsEvent.UpdateDetailsClicked -> {
                updateAdminDetails()
            }

            AdminDetailsEvent.VerifyEmailClicked -> {
                sendEmailVerificationCode()
            }

            is AdminDetailsEvent.EmailChanged -> {
                _state.update {
                    it.copy(
                        editableEmail = event.email
                    )
                }
            }

            is AdminDetailsEvent.UsernameChanged -> {
                _state.update {
                    it.copy(
                        editableUsername = event.username
                    )
                }
            }


            is AdminDetailsEvent.PasswordVisibilityChanged -> {
                _state.update {
                    it.copy(isPasswordVisible = event.isVisible)
                }
            }
        }

    }

    private fun updateAdminDetails() {


        val emailValidationError =
            Validators.validateEmail(email = _state.value.editableEmail)
        val usernameValidationError =
            Validators.validateUsername(username = _state.value.editableUsername)

        _state.update {
            it.copy(
                emailError = emailValidationError,
                usernameError = usernameValidationError
            )
        }

        if (emailValidationError != null || usernameValidationError != null) return

        // checking if any changes are made or not
        if (_state.value.editableEmail == _state.value.orgEmail &&
            _state.value.editableUsername == _state.value.orgUsername
        ) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent("No changes made to the email and username."))
            }
            return
        }

        updateAdminDetailsUseCase(
            UpdateAdminDetailsRequestBody(
                email = _state.value.editableEmail,
                username = _state.value.editableUsername
            )
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isUpdatingDetails = false,
                            isEditingDetails = true,
                            isUsernameEmailEnabled = true
                        )
                    }
                    result.message?.let { resultMessage ->
                        SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isUpdatingDetails = true,
                            emailError = null,
                            usernameError = null,
                            isEditingDetails = true,
                            isUsernameEmailEnabled = false
                        )
                    }
                }

                is Resource.Success -> {
                    SnackbarController.sendEvent(SnackbarEvent(message = "Details updated successfully."))

                    if (_state.value.orgEmail != result.data?.email) {
                        _state.update {
                            it.copy(isVerified = false)
                        }
                    }
                    _state.update {
                        it.copy(
                            isUpdatingDetails = false,
                            isEditingDetails = false,
                            orgEmail = result.data?.email
                                ?: "", // will never be "" bcz data is sent
                            orgUsername = result.data?.username ?: "",
                            emailError = null,
                            usernameError = null,
                            isUsernameEmailEnabled = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sendEmailVerificationCode() {
        sendVerificationCodeToEmailUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isSendingVerificationCode = false
                        )
                    }
                    result.message?.let { resultMessage ->
                        SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                    }

                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isSendingVerificationCode = true
                        )
                    }
                }

                is Resource.Success -> {
                    SnackbarController.sendEvent(SnackbarEvent(message = "Verification code sent successfully."))

                    _state.update {
                        it.copy(
                            isVerificationCodeSent = true,
                            isSendingVerificationCode = false
                        )
                    }

                    // bcz if the user click back button from verify code he must not be again automatically navigating to the verify code screen again
                    delay(1000L)
                    _state.update {
                        it.copy(
                            isVerificationCodeSent = false,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeAdmin() {
        viewModelScope.launch {
            removeAdminUseCase().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        result.message?.let { resultMessage ->
                            SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoggingOut = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        removeAuthTokensUseCase()
                        _state.update {
                            it.copy(
                                isLoggingOut = false,
                                isLoggedOut = true
                            )
                        }
                        SnackbarController.sendEvent(SnackbarEvent(message = "Account removed successfully."))

                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun getAdminDetails() {
        getAdminDetailsUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    result.message?.let { resultMessage ->
                        SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isInitialDataLoading = true
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isInitialDataLoading = false,
                            orgEmail = result.data?.email ?: "",
                            orgUsername = result.data?.username ?: "",
                            editableEmail = result.data?.email ?: "",
                            editableUsername = result.data?.username ?: "",
                            isVerified = result.data?.isVerified,
                        )
                    }

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun logoutAdmin() {
        logoutUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    result.message?.let { resultMessage ->
                        SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoggingOut = true
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoggingOut = false,
                            isLoggedOut = true
                        )
                    }
                    removeAuthTokensUseCase()
                    SnackbarController.sendEvent(SnackbarEvent(message = "Logged out successfully."))

                }
            }
        }.launchIn(viewModelScope)
    }
}