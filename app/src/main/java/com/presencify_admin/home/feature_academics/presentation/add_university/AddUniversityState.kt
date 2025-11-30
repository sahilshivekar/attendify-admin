package com.presencify_admin.home.feature_academics.presentation.add_university

data class AddUniversityState(
    val universityId: Int? = null,
    val name: String = "",
    val abbreviation: String = "",

    val nameError: String? = null,
    val abbreviationError: String? = null,

    val isLoading: Boolean = false,
    val isSubmitted: Boolean = false
)
