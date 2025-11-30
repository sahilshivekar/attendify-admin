package com.presencify_admin.home.feature_academics.presentation.manage_university

sealed interface ManageUniversityAction {
    data object FABClick : ManageUniversityAction
    data class UniversityListItemClick(val universityId: Int) : ManageUniversityAction
}
