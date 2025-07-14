package com.attendify_admin.home.feature_academics.presentation.manage_division

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class DivisionViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(DivisionState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = DivisionState()
        )

    fun onAction(action: DivisionAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}