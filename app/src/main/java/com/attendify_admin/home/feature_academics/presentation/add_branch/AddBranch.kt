package com.attendify_admin.home.feature_academics.presentation.add_branch

import android.R.attr.onClick
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.ui.theme.AttendifyAdminTheme

@Composable
fun AddBranchRoot(
    viewModel: AddBranchViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddBranchScreen(
        state = state,
        onAction = { action ->
            when (action) {
                AddBranchAction.OnAddUpdateSuccessNavigation -> {
                    navController.navigateUp()
                }
                else -> {
                    viewModel.onAction(action)
                }
            }
        }
    )
}

@Composable
fun AddBranchScreen(
    modifier: Modifier = Modifier,
    state: AddBranchState,
    onAction: (AddBranchAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(state.isSubmitted) {
        if (state.isSubmitted) {
            onAction(AddBranchAction.OnAddUpdateSuccessNavigation)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AttendifyTextField(
                value = state.name,
                onValueChange = { onAction(AddBranchAction.NameChanged(it)) },
                label = "Branch Name",
                isError = state.nameError != null,
                modifier = Modifier.fillMaxWidth(),
                supportingText = state.nameError
            )

            AttendifyTextField(
                value = state.abbreviation,
                onValueChange = { onAction(AddBranchAction.AbbreviationChanged(it)) },
                label = "Abbreviation",
                isError = state.abbreviationError != null,
                modifier = Modifier.fillMaxWidth(),
                supportingText = state.abbreviationError
            )
        }

        AttendifyButton(
            onClick = { onAction(AddBranchAction.SubmitClicked) },
            text = if (state.branchId == null) "Add Branch" else "Update Branch",
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
private fun Preview() {
    AttendifyAdminTheme {
        AddBranchScreen(
            state = AddBranchState(),
            onAction = {}
        )
    }
}