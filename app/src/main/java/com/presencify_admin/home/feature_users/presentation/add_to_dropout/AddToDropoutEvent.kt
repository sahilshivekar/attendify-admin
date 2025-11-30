package com.presencify_admin.home.feature_users.presentation.add_to_dropout

sealed interface AddToDropoutEvent {
    data class AddStudentToDropout(val studentId: Int) : AddToDropoutEvent
    data class AcademicYearDropdownVisibilityChanged(val isVisible: Boolean) : AddToDropoutEvent
    data class AcademicYearChanged(val newAcademicYear: String) : AddToDropoutEvent
}