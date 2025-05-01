package com.attendify_admin.admin_auth.presentation.verify_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.admin_auth.domain.use_case.SaveAccessTokenUseCase
import com.attendify_admin.admin_auth.domain.use_case.SaveRefreshTokenUseCase
import com.attendify_admin.admin_auth.domain.use_case.VerifyCodeUseCase
import com.attendify_admin.admin_auth.presentation.login.LoginEvent
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VerifyCodeViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase
) : ViewModel() {
    var state = MutableStateFlow(VerifyCodeState())
        private set

    fun onEvent(event: VerifyCodeEvent) {
        when (event) {
            is VerifyCodeEvent.CodeChanged -> {
                state.value = state.value.copy(code = event.code)
            }

            is VerifyCodeEvent.VerifyCodeClicked -> {
                if (state.value.email.isBlank()) {
                    state.value = state.value.copy(
                        codeError = "Code can't be blank"
                    )
                    return
                }
                verifyCode(code = state.value.code, email = state.value.email)
            }

            is VerifyCodeEvent.DismissAlertDialog -> {
                state.value = state.value.copy(isOtherError = null)
            }
        }
    }

    private fun verifyCode(code: String, email: String) {
        verifyCodeUseCase(code = code, email = email).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isLoading = true,
                        codeError = null
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
                                    codeError = msg
                                )
                            }
                        }
                    }
                }

                is Resource.Success -> {

                    result.data?.data?.let {
                        saveAccessTokenUseCase(it.accessToken)
                        saveRefreshTokenUseCase(it.refreshToken)
                    }

                    state.value = state.value.copy(
                        isLoading = false,
                        isVerified = true
                    )


                }

            }
        }.launchIn(viewModelScope)
    }

    fun setEmail(email: String) {
        state.value = state.value.copy(email = email)
    }
}