package com.attendify_admin.home.feature_academics.presentation.manage_scheme

sealed interface ManageSchemeAction {
    data object FABClick : ManageSchemeAction
    data class SearchQueryChanged(val query: String) : ManageSchemeAction
    data object FetchSchemes : ManageSchemeAction
    data class SchemeListItemClick(val schemeId: Int) : ManageSchemeAction
}
