package com.presencify_admin.common.presentation.components.top_bar

data class TopAppBarState(
    val title: String? = null,
    val isTopAppBarVisible: Boolean = false,
    val isBackIconButtonVisible: Boolean = false,
    val isProfileIconButtonVisible: Boolean = false,
    val isAppLogoNameVisible: Boolean = false
)

