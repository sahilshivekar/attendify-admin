package com.attendify_admin.home.feature_academics.presentation.add_branch

sealed interface AddBranchAction {
    data class NameChanged(val newName: String) : AddBranchAction
    data class AbbreviationChanged(val newAbbreviation: String) : AddBranchAction
    data object SubmitClicked : AddBranchAction
    data object OnAddUpdateSuccessNavigation: AddBranchAction
}