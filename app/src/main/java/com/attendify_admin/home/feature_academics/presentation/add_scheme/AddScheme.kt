package com.attendify_admin.home.feature_academics.presentation.add_scheme

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
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
fun AddSchemeRoot(
    viewModel: AddSchemeViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddSchemeScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is AddSchemeAction.OnAddUpdateSuccessNavigation -> {
                    navController.navigateUp()
                }
                else -> viewModel.onAction(action)
            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchemeScreen(
    modifier: Modifier = Modifier,
    state: AddSchemeState,
    onAction: (AddSchemeAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(state.isSubmitted) {
        if (state.isSubmitted) {
            onAction(AddSchemeAction.OnAddUpdateSuccessNavigation)
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
                onValueChange = { onAction(AddSchemeAction.NameChanged(it)) },
                label = "Scheme Name",
                isError = state.nameError != null,
                modifier = Modifier.fillMaxWidth(),
                supportingText = state.nameError
            )


            ExposedDropdownMenuBox(
                expanded = state.isUniversityDropDownOpen,
                onExpandedChange = {
                    onAction(AddSchemeAction.UniversityDropDownVisibilityChanged(it))
                }
            ) {
                AttendifyTextField(
                    value = state.selectedUniversity?.name ?: "",
                    onValueChange = {},
                    label = "University",
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isUniversityDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    isError = state.universityError != null,
                    supportingText = state.universityError
                )
                ExposedDropdownMenu(
                    expanded = state.isUniversityDropDownOpen,
                    onDismissRequest = {
                        onAction(AddSchemeAction.UniversityDropDownVisibilityChanged(false))
                        focusManager.clearFocus()
                    }
                ) {
                    state.universityOptions.forEach { university ->
                        DropdownMenuItem(
                            text = { Text(university.name) },
                            onClick = {
                                onAction(AddSchemeAction.UniversityChanged(university))
                                onAction(AddSchemeAction.UniversityDropDownVisibilityChanged(false))
                                focusManager.clearFocus()
                            }
                        )
                    }
                }
            }
        }

        AttendifyButton(
            onClick = { onAction(AddSchemeAction.SubmitClicked) },
            text = if (state.schemeId == null) "Add Scheme" else "Update Scheme",
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
private fun Preview() {
    AttendifyAdminTheme {
        AddSchemeScreen(
            state = AddSchemeState(),
            onAction = {}
        )
    }
}