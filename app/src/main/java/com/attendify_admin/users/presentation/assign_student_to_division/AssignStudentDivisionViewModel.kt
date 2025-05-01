package com.attendify_admin.users.presentation.assign_student_to_division

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AssignStudentDivisionViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(AssignStudentDivisionState())
        private set

    fun onEvent(event: AssignStudentDivisionEvent) {
        when (event) {
            AssignStudentDivisionEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is AssignStudentDivisionEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}