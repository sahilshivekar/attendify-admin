package com.presencify_admin.home.feature_academics.presentation.manage_course

import androidx.paging.PagingData
import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Course
import com.presencify_admin.common.domain.model.Scheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ManageCourseState(
    val searchQuery: String = "",
    val selectedBranch: Branch? = null,
    val selectedSemester: Int? = null,
    val selectedScheme: Scheme? = null,
    val branches: List<Branch> = emptyList(),
    val schemes: List<Scheme> = emptyList(),
    val isBranchesLoading: Boolean = false,
    val isSchemesLoading: Boolean = false,
    val isBottomSheetVisible: Boolean = false,
    val courses: Flow<PagingData<Course>> = emptyFlow()
)