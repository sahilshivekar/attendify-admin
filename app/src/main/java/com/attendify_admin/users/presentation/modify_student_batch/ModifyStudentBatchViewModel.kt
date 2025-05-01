package com.attendify_admin.users.presentation.modify_student_batch

import androidx.lifecycle.ViewModel
import com.attendify_admin.users.presentation.assign_student_to_division.AssignStudentDivisionEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ModifyStudentBatchViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(ModifyStudentBatchState())
        private set


    fun onEvent(event: ModifyStudentBatchEvent) {
        when (event) {
            ModifyStudentBatchEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is ModifyStudentBatchEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}