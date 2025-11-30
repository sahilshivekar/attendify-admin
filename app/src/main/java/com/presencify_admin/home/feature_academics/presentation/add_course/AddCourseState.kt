package com.presencify_admin.home.feature_academics.presentation.add_course

import com.presencify_admin.common.domain.model.Scheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


data class AddCourseState(
    val courseId: Int? = null,
    val code: String = "",
    val name: String = "",
    val optionalSubject: String = "",
    val schemeOptions: ImmutableList<Scheme> = persistentListOf(),
    val selectedScheme: Scheme? = null,
    val isSchemeDropDownOpen: Boolean = false,

    val codeError: String? = null,
    val nameError: String? = null,
    val optionalSubjectError: String? = null,
    val isSchemeError: String? = null,

    val isLoading: Boolean = false,
    val isSubmitted: Boolean = false,
)
