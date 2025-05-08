package com.attendify_admin.home.presentation

import androidx.lifecycle.ViewModel
import com.attendify_admin.common.presentation.components.top_bar.TopAppBarState
import com.attendify_admin.home.navigation.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var state = MutableStateFlow(
        TopAppBarState(
            isTopAppBarVisible = true,
            isProfileIconButtonVisible = true
        )
    )
        private set

    private fun updateTitle(title: String?) {
        state.value = state.value.copy(title = title)
    }

    fun updateTopAppBarForRoute(currentDestinationRoute: String?) {

        when (currentDestinationRoute) {

            HomeDestination.Schedule.route -> {
                updateTitle("Schedule")
            }

            HomeDestination.Announcements.route -> {
                updateTitle("Announcements")
            }

            HomeDestination.Academics.route -> {
                updateTitle("Academics")
            }

            HomeDestination.Users.route -> {
                updateTitle("Users")
            }

        }
    }
}