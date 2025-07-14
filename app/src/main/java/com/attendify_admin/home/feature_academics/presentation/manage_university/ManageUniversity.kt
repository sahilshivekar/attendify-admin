package com.attendify_admin.home.feature_academics.presentation.manage_university

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.attendify_admin.home.feature_academics.navigation.AcademicsDestination
import com.attendify_admin.home.feature_academics.presentation.manage_university.components.UniversityListItem
import com.attendify_admin.ui.theme.AttendifyAdminTheme

@Composable
fun UniversityRoot(
    viewModel: ManageUniversityViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ManageUniversityScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ManageUniversityAction.FABClick -> {
                    navController.navigate(AcademicsDestination.AddUniversity.route)
                }

                is ManageUniversityAction.UniversityListItemClick -> {
                    navController.navigate(AcademicsDestination.UniversityDetails.route + "?universityId=${action.universityId}")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageUniversityScreen(
    state: ManageUniversityState,
    onAction: (ManageUniversityAction) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(ManageUniversityAction.FABClick)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add University")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Universities",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${state.error}")
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.universities, key = { it.id }) { university ->
                            UniversityListItem(
                                university = university,
                                onClick = {
                                    onAction(ManageUniversityAction.UniversityListItemClick(university.id))
                                }
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
    AttendifyAdminTheme {
        ManageUniversityScreen(
            state = ManageUniversityState(),
            onAction = {}
        )
    }
}