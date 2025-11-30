package com.presencify_admin.home.feature_academics.presentation.manage_branch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.presencify_admin.home.feature_academics.presentation.manage_branch.components.BranchListItem
import com.presencify_admin.ui.theme.PresencifyAdminTheme

@Composable
fun BranchRoot(
    viewModel: ManageBranchViewModel,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ManageBranchScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ManageBranchAction.FABClick -> {
                    navController.navigate(AcademicsDestination.AddBranch.route)
                }

                is ManageBranchAction.BranchListItemClick -> {
                    navController.navigate(AcademicsDestination.BranchDetails.route + "?branchId=${action.branchId}")
                }

                else -> viewModel.onAction(action)
            }
        }
    )
}


// ManageBranchScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageBranchScreen(
    state: ManageBranchState,
    onAction: (ManageBranchAction) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(ManageBranchAction.FABClick)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Branch")
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
                    onAction(ManageBranchAction.SearchQueryChanged(it))
                },
                onSearchIconClick = {
                    onAction(ManageBranchAction.FetchBranches)
                },
                searchBarPlaceholder = "Search Branch"
            )

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (state.error != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.error}")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.branches, key = { it.id }) { branch ->
                        BranchListItem(
                            branch = branch,
                            onClick = { onAction(ManageBranchAction.BranchListItemClick(branch.id)) }
                        )
                        Spacer(Modifier.height(8.dp))
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
        ManageBranchScreen(
            state = ManageBranchState(),
            onAction = {}
        )
    }
}