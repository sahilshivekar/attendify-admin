package com.attendify_admin.home.feature_academics.presentation.add_university

sealed interface AddUniversityAction {
    data class NameChanged(val newName: String) : AddUniversityAction
    data class AbbreviationChanged(val newAbbreviation: String) : AddUniversityAction
    data object SubmitClicked : AddUniversityAction
    data object OnAddUpdateSuccessNavigation : AddUniversityAction
}
