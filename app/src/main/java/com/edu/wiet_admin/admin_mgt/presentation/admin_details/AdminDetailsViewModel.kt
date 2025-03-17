package com.edu.wiet_admin.admin_mgt.presentation.admin_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.wiet_admin.admin_auth.domain.use_case.LogoutUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.RemoveAuthTokensUseCase
import com.edu.wiet_admin.admin_mgt.data.remote.UpdateAdminDetailsRequestBody
import com.edu.wiet_admin.admin_mgt.domain.use_case.GetAdminDetailsUseCase
import com.edu.wiet_admin.admin_mgt.domain.use_case.RemoveAdminUseCase
import com.edu.wiet_admin.admin_mgt.domain.use_case.SendVerificationCodeToEmailUseCase
import com.edu.wiet_admin.admin_mgt.domain.use_case.UpdateAdminDetailsUseCase
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.common.validation.Validators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminDetailsViewModel @Inject constructor(
    private val getAdminDetailsUseCase: GetAdminDetailsUseCase,
    private val removeAuthTokensUseCase: RemoveAuthTokensUseCase,
    private val removeAdminUseCase: RemoveAdminUseCase,
    private val sendVerificationCodeToEmailUseCase: SendVerificationCodeToEmailUseCase,
    private val updateAdminDetailsUseCase: UpdateAdminDetailsUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var state = MutableStateFlow(AdminDetailsState())
        private set


    init {
        getAdminDetails()
    }

    fun onEvent(event: AdminDetailsEvent) {
        when (event) {
            AdminDetailsEvent.CancelEditingDetailsClicked -> {
                state.value = state.value.copy(
                    isEditingDetails = false,
                    isUsernameEmailEnabled = false,
                    editableEmail = state.value.orgEmail,
                    editableUsername = state.value.orgUsername,
                    emailError = null,
                    usernameError = null
                )
            }


            AdminDetailsEvent.EditDetailsClicked -> {
                state.value = state.value.copy(
                    isEditingDetails = true,
                    isUsernameEmailEnabled = true
                )
            }

            AdminDetailsEvent.LogoutClicked -> {
                logoutAdmin()
            }

            AdminDetailsEvent.DismissRemoveAccountClicked -> {
                state.value = state.value.copy(
                    showRemoveAccountConfirmationDialog = false
                )
            }

            AdminDetailsEvent.RemoveAdminClicked -> {
                state.value = state.value.copy(
                    showRemoveAccountConfirmationDialog = true
                )
            }

            AdminDetailsEvent.RemoveAdminConfirmed -> {
                removeAdmin()
            }

            AdminDetailsEvent.UpdateDetailsClicked -> {
                updateAdminDetails()
            }

            AdminDetailsEvent.VerifyEmailClicked -> {
                sendEmailVerificationCode()
            }

            is AdminDetailsEvent.EmailChanged -> {
                state.value = state.value.copy(
                    editableEmail = event.email
                )
            }

            is AdminDetailsEvent.UsernameChanged -> {
                state.value = state.value.copy(
                    editableUsername = event.username
                )
            }

            AdminDetailsEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    alertMessage = null
                )
            }

            is AdminDetailsEvent.PasswordVisibilityChanged -> {
                state.value = state.value.copy(isPasswordVisible = event.isVisible)
            }
        }
    }


    private fun updateAdminDetails() {



        val emailValidationError = Validators.validateEmail(email = state.value.editableEmail)
        val usernameValidationError =
            Validators.validateUsername(username = state.value.editableUsername)

        state.value = state.value.copy(
            emailError = emailValidationError,
            usernameError = usernameValidationError
        )

        if (emailValidationError != null || usernameValidationError != null) return

        // checking if any changes are made or not
        if (state.value.editableEmail == state.value.orgEmail &&
            state.value.editableUsername == state.value.orgUsername
        ) {
            state.value = state.value.copy(
                alertMessage = "No changes made to the email and username",
            )
            return
        }

        updateAdminDetailsUseCase(
            UpdateAdminDetailsRequestBody(
                email = state.value.editableEmail,
                username = state.value.editableUsername
            )
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        isUpdatingDetails = false,
                        isEditingDetails = true,
                        isUsernameEmailEnabled = true
                    )
                    result.message?.let { resultMessage ->
                        if (RemoteUtils.isKnownError(resultMessage)) {
                            state.value = state.value.copy(alertMessage = resultMessage)
                        } else if (resultMessage.contains("email", ignoreCase = true)) {
                            state.value = state.value.copy(
                                emailError = resultMessage,
                                usernameError = null
                            )
                        } else if (resultMessage.contains(
                                "username",
                                ignoreCase = true
                            )
                        ) {
                            state.value = state.value.copy(
                                usernameError = resultMessage,
                                emailError = null
                            )
                        } else {
                            state.value = state.value.copy(
                                alertMessage = resultMessage,
                                emailError = null,
                                usernameError = null
                            )
                        }
                    }
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isUpdatingDetails = true,
                        emailError = null,
                        usernameError = null,
                        isEditingDetails = true,
                        isUsernameEmailEnabled = false
                    )
                }

                is Resource.Success -> {
                    if (state.value.orgEmail != result.data?.data?.email) {
                        state.value = state.value.copy(isVerified = false)
                    }
                    state.value = state.value.copy(
                        isUpdatingDetails = false,
                        isEditingDetails = false,
                        orgEmail = result.data?.data?.email
                            ?: "", // will never be "" bcz data is sent
                        orgUsername = result.data?.data?.username ?: "",
                        alertMessage = null,
                        emailError = null,
                        usernameError = null,
                        isUsernameEmailEnabled = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sendEmailVerificationCode() {
        sendVerificationCodeToEmailUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        alertMessage = result.message
                    )
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isSendingVerificationCode = true
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isVerificationCodeSent = true,
                        isSendingVerificationCode = false
                    )

                    // bcz if the user click back button from verify code he must not be again automatically navigating to the verify code screen again
                    delay(1000L)
                    state.value = state.value.copy(
                        isVerificationCodeSent = false,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeAdmin() {
        viewModelScope.launch {

            removeAdminUseCase().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            alertMessage = result.message
                        )
                    }

                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            isLoggingOut = true
                        )
                    }

                    is Resource.Success -> {
                        removeAuthTokensUseCase()
                        state.value = state.value.copy(
                            isLoggingOut = false,
                            isLoggedOut = true
                        )
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun getAdminDetails() {
        getAdminDetailsUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        alertMessage = result.message,
                    )
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isInitialDataLoading = true
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isInitialDataLoading = false,
                        orgEmail = result.data?.data?.email ?: "",
                        orgUsername = result.data?.data?.username ?: "",
                        editableEmail = result.data?.data?.email ?: "",
                        editableUsername = result.data?.data?.username ?: "",
//                        password = result.data?.data?.password ?: "", // not of use
                        isVerified = result.data?.data?.isVerified,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun logoutAdmin() {
        logoutUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        alertMessage = result.message
                    )
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isLoggingOut = true
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isLoggingOut = false,
                        isLoggedOut = true
                    )
                    removeAuthTokensUseCase()
                }
            }
        }.launchIn(viewModelScope)
    }
}