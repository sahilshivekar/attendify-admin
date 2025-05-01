package com.attendify_admin.users.presentation.assign_student_to_semester

import androidx.lifecycle.ViewModel
import com.attendify_admin.users.presentation.assign_student_to_division.AssignStudentDivisionEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AssignStudentSemesterViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(AssignStudentSemesterState())
        private set


    fun onEvent(event: AssignStudentSemesterEvent) {
        when (event) {
            AssignStudentSemesterEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is AssignStudentSemesterEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}