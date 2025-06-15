package com.attendify_admin.common.presentation.components.bottom_bar

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun AttendifyBottomNavigationBar(
    currentDestinationRoute: String?,
    homeNavController: NavHostController
) {

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        bottomNavBarItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestinationRoute == item.route,
                onClick = {
                    homeNavController.navigate(item.route) {
                        popUpTo(id = homeNavController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if (currentDestinationRoute == item.route) {
                        Icon(
                            painter = painterResource(item.selectedIcon),
                            contentDescription = item.label,
                            modifier = Modifier
                                .height(28.dp)
                                .width(28.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(item.unselectedIcon),
                            contentDescription = item.label,
                            modifier = Modifier
                                .height(28.dp)
                                .width(28.dp)
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


