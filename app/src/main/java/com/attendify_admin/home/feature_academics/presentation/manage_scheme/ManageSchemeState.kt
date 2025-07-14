package com.attendify_admin.home.feature_academics.presentation.manage_scheme

import com.attendify_admin.common.domain.model.Scheme

data class ManageSchemeState(
    val searchQuery: String = "",
    val schemes: List<Scheme> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
