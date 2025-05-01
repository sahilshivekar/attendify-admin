package com.attendify_admin.common.presentation.scaffold.top_bar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopAppBarViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(TopAppBarState())
        private set

    fun updateTitle(title: String?){
        state = state.copy(title = title)
    }

    fun updateBarVisibility(isBarVisible: Boolean){
        state = state.copy(isBarVisible = isBarVisible)
    }

    fun updateBackButtonVisibility(isBackButtonVisible: Boolean){
        state = state.copy(isBackButtonVisible = isBackButtonVisible)
    }

    fun updateProfileButtonVisibility(isProfileButtonVisible: Boolean){
        state = state.copy(isProfileButtonVisible = isProfileButtonVisible)
    }

}