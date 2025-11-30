package com.presencify_admin.home.feature_academics.presentation.manage_scheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.presencify_admin.common.presentation.components.PresencifySearchBar
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination
import com.presencify_admin.home.feature_academics.presentation.manage_scheme.components.SchemeListItem
import com.presencify_admin.ui.theme.PresencifyAdminTheme

@Composable
fun ManageSchemeRoot(
    viewModel: ManageSchemeViewModel,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ManageSchemeScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ManageSchemeAction.FABClick -> {
                    navController.navigate(AcademicsDestination.AddScheme.route)
                }
                is ManageSchemeAction.SchemeListItemClick -> {
                    navController.navigate(AcademicsDestination.SchemeDetails.route + "?schemeId=${action.schemeId}")
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageSchemeScreen(
    state: ManageSchemeState,
    onAction: (ManageSchemeAction) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(ManageSchemeAction.FABClick)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Scheme")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            PresencifySearchBar(
                searchQuery = state.searchQuery,
                onSearchQueryValueChange = {
                    onAction(ManageSchemeAction.SearchQueryChanged(it))
                },
                onSearchIconClick = {
                    onAction(ManageSchemeAction.FetchSchemes)
                },
                searchBarPlaceholder = "Search Scheme"
            )

            when {
                state.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${state.error}")
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.schemes, key = { it.id }) { scheme ->
                            SchemeListItem(
                                scheme = scheme,
                                onClick = { onAction(ManageSchemeAction.SchemeListItemClick(scheme.id)) }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    PresencifyAdminTheme {
        ManageSchemeScreen(
            state = ManageSchemeState(),
            onAction = {}
        )
    }
}