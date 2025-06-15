package com.attendify_admin.home.feature_users.presentation.staff_details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class StaffDetailsViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(StaffDetailsState())
        private set


    fun onEvent(event: StaffDetailsEvent) {
        when (event) {
            StaffDetailsEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is StaffDetailsEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}