package com.edu.wiet_admin.common.presentation.scaffold.bottom_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.edu.wiet_admin.R
import com.edu.wiet_admin.common.Constants
import com.edu.wiet_admin.navigation.AppDestination


@Composable
fun WietBottomNavigationBar(
    currDest: String?,
    rootNavController: NavHostController
) {

    val bottomNavBarItems = listOf(
        BottomNavBarItem(
            unselectedIcon = R.drawable.sharp_person_24,
            selectedIcon = R.drawable.baseline_person_24,
            label = "Users",
            route = AppDestination.Users.route
        ),
        BottomNavBarItem(
            unselectedIcon = R.drawable.clock_icon,
            selectedIcon = R.drawable.clock_icon_filled,
            label = "Schedule",
            route = AppDestination.Schedule.route
        ),
        BottomNavBarItem(
            unselectedIcon = R.drawable.notification_icon,
            selectedIcon = R.drawable.notification_icon_filled,
            label = "Announcement",
            route = AppDestination.Announcements.route
        ),
        BottomNavBarItem(
            unselectedIcon = R.drawable.academics_icon,
            selectedIcon = R.drawable.academics_icon_filled,
            label = "Academics",
            route = AppDestination.Academics.route
        )
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        bottomNavBarItems.forEach { item ->
            NavigationBarItem(
                selected = currDest == item.route,
                onClick = {
                    rootNavController.navigate(item.route) {
                        popUpTo(id = rootNavController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if (currDest == item.route) {
                        Icon(
                            painter = painterResource(item.selectedIcon),
                            contentDescription = item.label,
                            modifier = Modifier.height(Constants.ICON_SIZE).width(Constants.ICON_SIZE)
                        )
                    } else {
                        Icon(
                            painter = painterResource(item.unselectedIcon),
                            contentDescription = item.label,
                            modifier = Modifier.height(Constants.ICON_SIZE).width(Constants.ICON_SIZE)
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    selectedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface, // will not affect as I am not gonna disable any item
                    disabledTextColor = MaterialTheme.colorScheme.onSurface // will not affect as I am not gonna disable any item
                )
            )
        }
    }
}


