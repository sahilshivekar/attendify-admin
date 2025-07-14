package com.attendify_admin.home.feature_academics.presentation.add_scheme

import com.attendify_admin.common.domain.model.University
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class AddSchemeState(
    val schemeId: Int? = null,
    val name: String = "",
    val abbreviation: String = "",
    val selectedUniversity: University? = null,
    val universityOptions: ImmutableList<University> = persistentListOf(),
    val isUniversityDropDownOpen: Boolean = false,

    val nameError: String? = null,
    val abbreviationError: String? = null,
    val universityError: String? = null,

    val isLoading: Boolean = false,
    val isSubmitted: Boolean = false
)
