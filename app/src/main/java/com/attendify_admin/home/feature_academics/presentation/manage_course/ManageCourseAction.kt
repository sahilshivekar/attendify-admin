package com.attendify_admin.home.feature_academics.presentation.manage_course

import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Scheme

sealed interface ManageCourseAction {
    data object FABClick : ManageCourseAction
    data class SearchQueryChanged(val query: String) : ManageCourseAction
    data object FetchCourses : ManageCourseAction
    data class CourseClick(val id: Int) : ManageCourseAction
    data class SelectedBranchChanged(val branch: Branch?) : ManageCourseAction
    data class SelectedSemesterChanged(val semester: Int?) : ManageCourseAction
    data class SelectedSchemeChanged(val scheme: Scheme?) : ManageCourseAction
    data object BottomSheetDismissed : ManageCourseAction
    data object ApplyFilters : ManageCourseAction
    data object ResetFilters : ManageCourseAction
}