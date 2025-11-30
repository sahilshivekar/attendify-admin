package com.presencify_admin.common.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.feature_admin_auth.domain.use_case.ReadAccessTokenUseCase
import com.presencify_admin.root_navigation.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class InitialDestinationViewModel @Inject constructor(
    readAccessTokenUseCase: ReadAccessTokenUseCase
) : ViewModel() {

    var state by mutableStateOf<String?>(null)
        private set

    init {
        readAccessTokenUseCase().onEach { token ->
            state = if (token == null) {
                AppDestination.AdminAuth.route
            } else {
                AppDestination.HomeScaffold.route
            }
        }.launchIn(viewModelScope)
    }

}