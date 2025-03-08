package com.edu.wiet_admin.common.presentation.scaffold.bottom_bar

import androidx.annotation.DrawableRes

data class BottomNavBarItem(
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    val label: String,
    val route: String
)
