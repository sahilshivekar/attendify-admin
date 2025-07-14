package com.attendify_admin.home.feature_academics.presentation.add_course

import com.attendify_admin.common.domain.model.Scheme

// AddCourseAction.kt
sealed interface AddCourseAction {
    data class CodeChanged(val newCode: String) : AddCourseAction
    data class NameChanged(val newName: String) : AddCourseAction
    data class OptionalSubjectChanged(val newOptionalSubject: String) : AddCourseAction
    data class SchemeChanged(val newScheme: Scheme) : AddCourseAction
    data class SchemeDropDownVisibilityChanged(val isVisible: Boolean) : AddCourseAction
    data object SubmitClicked : AddCourseAction
    data object OnAddUpdateSuccessNavigation : AddCourseAction
}