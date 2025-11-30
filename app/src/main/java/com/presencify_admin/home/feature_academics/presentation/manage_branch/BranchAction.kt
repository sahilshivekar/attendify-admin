package com.presencify_admin.home.feature_academics.presentation.manage_branch

sealed interface ManageBranchAction {
    data class SearchQueryChanged(val newQuery: String) : ManageBranchAction
    data object FetchBranches : ManageBranchAction
    data class DeleteBranch(val branchId: Int) : ManageBranchAction
    data object FABClick : ManageBranchAction
    data class BranchListItemClick(val branchId: Int) : ManageBranchAction
}
