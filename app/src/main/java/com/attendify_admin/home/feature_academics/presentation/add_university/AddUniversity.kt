package com.attendify_admin.home.feature_academics.presentation.add_university

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.ui.theme.AttendifyAdminTheme

@Composable
fun AddUniversityRoot(
    viewModel: AddUniversityViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddUniversityScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is AddUniversityAction.OnAddUpdateSuccessNavigation -> navController.navigateUp()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUniversityScreen(
    modifier: Modifier = Modifier,
    state: AddUniversityState,
    onAction: (AddUniversityAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(state.isSubmitted) {
        if (state.isSubmitted) {
            onAction(AddUniversityAction.OnAddUpdateSuccessNavigation)
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
                onValueChange = { onAction(AddUniversityAction.NameChanged(it)) },
                label = "University Name",
                isError = state.nameError != null,
                supportingText = state.nameError,
                modifier = Modifier.fillMaxWidth()
            )

            AttendifyTextField(
                value = state.abbreviation,
                onValueChange = { onAction(AddUniversityAction.AbbreviationChanged(it)) },
                label = "Abbreviation",
                isError = state.abbreviationError != null,
                supportingText = state.abbreviationError,
                modifier = Modifier.fillMaxWidth()
            )
        }

        AttendifyButton(
            onClick = { onAction(AddUniversityAction.SubmitClicked) },
            text = if (state.universityId == null) "Add University" else "Update University",
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AttendifyAdminTheme {
        AddUniversityScreen(
            state = AddUniversityState(),
            onAction = {}
        )
    }
}