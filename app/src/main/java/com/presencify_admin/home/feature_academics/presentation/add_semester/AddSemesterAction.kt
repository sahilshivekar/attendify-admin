package com.presencify_admin.home.feature_academics.presentation.add_semester

import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Scheme

sealed interface AddSemesterAction {
    data class BranchChanged(val branch: Branch) : AddSemesterAction
    data class AcademicYearChanged(val year: String) : AddSemesterAction
    data class SemesterNumberChanged(val number: Int) : AddSemesterAction
    data class SchemeChanged(val scheme: Scheme) : AddSemesterAction
    data class StartDateChanged(val date: String) : AddSemesterAction
    data class EndDateChanged(val date: String) : AddSemesterAction

    data class BranchDropdownVisibilityChanged(val visible: Boolean) : AddSemesterAction
    data class AcademicYearDropdownVisibilityChanged(val visible: Boolean) : AddSemesterAction
    data class SemesterNumberDropdownVisibilityChanged(val visible: Boolean) : AddSemesterAction
    data class SchemeDropdownVisibilityChanged(val visible: Boolean) : AddSemesterAction

    data object ShowStartDatePicker : AddSemesterAction
    data object ShowEndDatePicker : AddSemesterAction
    data object HideStartDatePicker : AddSemesterAction
    data object HideEndDatePicker : AddSemesterAction

    data object SubmitClicked : AddSemesterAction
    data object OnAddUpdateSuccessNavigation : AddSemesterAction
}