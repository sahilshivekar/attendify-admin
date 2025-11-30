package com.presencify_admin.common.presentation.components.bottom_bar

import androidx.annotation.DrawableRes
import com.presencify_admin.R
import com.presencify_admin.home.navigation.HomeDestination

data class BottomNavBarItem(
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    val label: String,
    val route: String
)

val bottomNavBarItems = listOf(
    BottomNavBarItem(
        unselectedIcon = R.drawable.sharp_person_24,
        selectedIcon = R.drawable.baseline_person_24,
        label = "Users",
        route = HomeDestination.Users.route
    ),
    BottomNavBarItem(
        unselectedIcon = R.drawable.clock_icon,
        selectedIcon = R.drawable.clock_icon_filled,
        label = "Schedule",
        route = HomeDestination.Schedule.route
    ),
    BottomNavBarItem(
        unselectedIcon = R.drawable.academics_icon,
        selectedIcon = R.drawable.academics_icon_filled,
        label = "Academics",
        route = HomeDestination.Academics.route
    )
)
