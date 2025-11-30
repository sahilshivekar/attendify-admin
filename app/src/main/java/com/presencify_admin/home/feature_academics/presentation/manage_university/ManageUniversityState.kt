package com.presencify_admin.home.feature_academics.presentation.manage_university

import com.presencify_admin.common.domain.model.University

data class ManageUniversityState(
    val universities: List<University> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
