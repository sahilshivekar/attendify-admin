package com.presencify_admin.feature_admin_auth.presentation.verify_code

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.feature_admin_auth.domain.use_case.SaveAccessTokenUseCase
import com.presencify_admin.feature_admin_auth.domain.use_case.SaveRefreshTokenUseCase
import com.presencify_admin.feature_admin_auth.domain.use_case.VerifyCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VerifyCodeViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(VerifyCodeState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("email")?.let { email ->
            _state.update {
                it.copy(email = email)
            }
        }
    }

    fun onEvent(event: VerifyCodeEvent) {
        when (event) {

            is VerifyCodeEvent.CodeChanged -> {
                _state.update {
                    it.copy(code = event.code)
                }
            }

            is VerifyCodeEvent.VerifyCodeClicked -> {
                verifyCode(code = _state.value.code, email = _state.value.email)
            }

        }
    }

    private fun verifyCode(code: String, email: String) {

        _state.update {
            it.copy(
                codeError = null
            )
        }

        if (_state.value.code.isBlank()) {
            _state.update {
                it.copy(
                    codeError = "Code can't be blank"
                )
            }
            return
        }

        if (_state.value.email.isBlank()) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent(message = "Some technical error occurred please restart the app"))
            }
            return
        }

        verifyCodeUseCase(code = code, email = email).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            codeError = null
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    result.message?.let { resultMessage ->
                        SnackbarController.sendEvent(SnackbarEvent(message = resultMessage))
                    }
                }

                is Resource.Success -> {

                    result.data?.let {
                        saveAccessTokenUseCase(it.accessToken)
                        saveRefreshTokenUseCase(it.refreshToken)
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            isVerified = true
                        )
                    }

                    SnackbarController.sendEvent(SnackbarEvent("Verification successful."))

                }
            }
        }.launchIn(viewModelScope)
    }
}