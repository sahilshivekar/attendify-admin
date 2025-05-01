package com.attendify_admin.users.presentation.remove_student_from_semester

import androidx.lifecycle.ViewModel
import com.attendify_admin.users.presentation.assign_student_to_division.AssignStudentDivisionEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RemoveStudentSemesterViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(RemoveStudentSemesterState())
        private set


    fun onEvent(event: RemoveStudentSemesterEvent) {
        when (event) {
            RemoveStudentSemesterEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is RemoveStudentSemesterEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}