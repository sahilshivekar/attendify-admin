package com.attendify_admin.home.users.presentation.assign_student_to_batch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AssignStudentBatchViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(AssignStudentBatchState())
        private set

    fun onEvent(event: AssignStudentBatchEvent) {
        when (event) {
            AssignStudentBatchEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is AssignStudentBatchEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}