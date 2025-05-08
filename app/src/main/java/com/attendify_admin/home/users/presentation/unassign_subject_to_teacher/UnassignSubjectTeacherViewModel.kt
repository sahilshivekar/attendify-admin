package com.attendify_admin.home.users.presentation.unassign_subject_to_teacher

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UnassignSubjectTeacherViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(UnassignSubjectTeacherState())
        private set


    fun onEvent(event: UnassignSubjectTeacherEvent) {
        when (event) {
            UnassignSubjectTeacherEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is UnassignSubjectTeacherEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}