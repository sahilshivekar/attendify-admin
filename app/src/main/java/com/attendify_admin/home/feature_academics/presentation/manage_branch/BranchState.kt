package com.attendify_admin.home.feature_academics.presentation.manage_branch

import com.attendify_admin.common.domain.model.Branch
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ManageBranchState(
    val searchQuery: String = "",
    val branches: ImmutableList<Branch> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)