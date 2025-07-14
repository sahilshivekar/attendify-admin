package com.attendify_admin.home.feature_academics.presentation.add_branch


data class AddBranchState(
    val name: String = "",
    val abbreviation: String = "",
    val isLoading: Boolean = false,
    val isSubmitted: Boolean = false,
    val nameError: String? = null,
    val abbreviationError: String? = null,
    val branchId: Int? = null,
)