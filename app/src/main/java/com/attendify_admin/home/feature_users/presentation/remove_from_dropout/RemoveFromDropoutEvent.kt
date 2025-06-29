package com.attendify_admin.home.feature_users.presentation.remove_from_dropout


sealed interface RemoveFromDropoutEvent {
    data class RemoveStudentFromDropout(val studentId: Int) : RemoveFromDropoutEvent
    data class AcademicYearDropdownVisibilityChanged(val isVisible: Boolean) : RemoveFromDropoutEvent
    data class AcademicYearChanged(val newAcademicYear: String) : RemoveFromDropoutEvent
}