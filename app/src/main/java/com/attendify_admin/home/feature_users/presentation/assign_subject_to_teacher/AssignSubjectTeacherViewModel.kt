package com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AssignSubjectTeacherViewModel @Inject constructor(

) : ViewModel() {
    var state = MutableStateFlow(AssignSubjectTeacherState())
        private set


    fun onEvent(event: AssignSubjectTeacherEvent) {
        when (event) {
            AssignSubjectTeacherEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }
            is AssignSubjectTeacherEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }
        }
    }
}