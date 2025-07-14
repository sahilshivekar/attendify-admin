package com.attendify_admin.home.feature_academics.presentation.academics_dashboard

sealed interface AcademicsDashboardAction {
    data object OnManageBranchClick : AcademicsDashboardAction
    data object OnManageCourseClick : AcademicsDashboardAction
    data object OnManageSchemeClick : AcademicsDashboardAction
    data object OnManageUniversityClick : AcademicsDashboardAction
    data object OnManageSemesterClick : AcademicsDashboardAction
    data object OnManageDivisionClick : AcademicsDashboardAction
    data object OnManageBatchClick : AcademicsDashboardAction
}
