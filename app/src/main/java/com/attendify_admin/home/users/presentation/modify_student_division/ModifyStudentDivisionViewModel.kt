package com.attendify_admin.home.users.presentation.modify_student_division

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ModifyStudentDivisionViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(ModifyStudentDivisionState())
        private set


    fun onEvent(event: ModifyStudentDivisionEvent) {
        when (event) {
            ModifyStudentDivisionEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is ModifyStudentDivisionEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}