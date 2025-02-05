package com.edu.wiet_admin.common.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.wiet_admin.admin_auth.domain.use_case.GetAccessRefreshTokenUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.ReadAccessTokenUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.ReadRefreshTokenUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.SaveAccessTokenUseCase
import com.edu.wiet_admin.admin_auth.domain.use_case.SaveRefreshTokenUseCase
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.navigation.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialDestinationViewModel @Inject constructor(
    private val readAccessTokenUseCase: ReadAccessTokenUseCase,
    private val readRefreshTokenUseCase: ReadRefreshTokenUseCase,
    private val getAccessRefreshTokenUseCase: GetAccessRefreshTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase
) : ViewModel() {

    var state by mutableStateOf<String?>(null)
        private set

    init {
        viewModelScope.launch {
            checkLoginStatus()
        }
    }

    private suspend fun checkLoginStatus() {
        val accessToken = readAccessTokenUseCase().first()
        if (accessToken != null) {
            state = AppDestination.HomeScreen.route
        } else {
            checkRefreshToken()
        }
    }


    private suspend fun checkRefreshToken() {
        val refreshToken = readRefreshTokenUseCase().first()
        if (refreshToken != null) {
            getAndSaveAccessRefreshToken(refreshToken)
        } else {
            state = AppDestination.AdminAuth.route
        }
    }

    private fun getAndSaveAccessRefreshToken(refreshToken: String) {
        getAccessRefreshTokenUseCase(refreshToken).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit

                is Resource.Error -> state = AppDestination.AdminAuth.route

                is Resource.Success -> {
                    result.data?.data?.accessToken?.let { saveAccessTokenUseCase(it) }
                    result.data?.data?.refreshToken?.let { saveRefreshTokenUseCase(it) }
                    state = AppDestination.HomeScreen.route
                    Log.d("InitialDest", "Got new tokens")
                }

            }
        }.launchIn(viewModelScope)
    }
}