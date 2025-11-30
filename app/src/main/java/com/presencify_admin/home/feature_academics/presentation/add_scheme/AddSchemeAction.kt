package com.presencify_admin.home.feature_academics.presentation.add_scheme

import com.presencify_admin.common.domain.model.University

sealed interface AddSchemeAction {
    data class NameChanged(val newName: String) : AddSchemeAction
    data class AbbreviationChanged(val newAbbreviation: String) : AddSchemeAction
    data class UniversityChanged(val newUniversity: University) : AddSchemeAction
    data class UniversityDropDownVisibilityChanged(val isVisible: Boolean) : AddSchemeAction
    data object SubmitClicked : AddSchemeAction
    data object OnAddUpdateSuccessNavigation : AddSchemeAction
}