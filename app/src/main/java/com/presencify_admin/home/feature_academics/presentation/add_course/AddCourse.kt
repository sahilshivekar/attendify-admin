package com.presencify_admin.home.feature_academics.presentation.add_course

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
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyTextField
import com.presencify_admin.ui.theme.PresencifyAdminTheme

@Composable
fun AddCourseRoot(
    viewModel: AddCourseViewModel,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddCourseScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is AddCourseAction.OnAddUpdateSuccessNavigation -> {
                    navController.navigateUp()
                }

                else -> {
                    viewModel.onAction(action)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(
    modifier: Modifier = Modifier,
    state: AddCourseState,
    onAction: (AddCourseAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(state.isSubmitted) {
        if (state.isSubmitted) {
            onAction(AddCourseAction.OnAddUpdateSuccessNavigation)
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
            PresencifyTextField(
                value = state.code,
                onValueChange = { onAction(AddCourseAction.CodeChanged(it)) },
                label = "Course Code",
                isError = state.codeError != null,
                modifier = Modifier.fillMaxWidth(),
                supportingText = state.codeError
            )

            PresencifyTextField(
                value = state.name,
                onValueChange = { onAction(AddCourseAction.NameChanged(it)) },
                label = "Course Name",
                isError = state.nameError != null,
                modifier = Modifier.fillMaxWidth(),
                supportingText = state.nameError
            )

            PresencifyTextField(
                value = state.optionalSubject,
                onValueChange = { onAction(AddCourseAction.OptionalSubjectChanged(it)) },
                label = "Optional Subject",
                isError = state.optionalSubjectError != null,
                modifier = Modifier.fillMaxWidth(),
                supportingText = state.optionalSubjectError
            )

            ExposedDropdownMenuBox(
                expanded = state.isSchemeDropDownOpen,
                onExpandedChange = {
                    onAction(AddCourseAction.SchemeDropDownVisibilityChanged(it))
                }
            ) {
                PresencifyTextField(
                    value = state.selectedScheme?.name ?: "",
                    onValueChange = {},
                    label = "Scheme",
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isSchemeDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    isError = state.isSchemeError != null,
                    supportingText = state.isSchemeError
                )
                ExposedDropdownMenu(
                    expanded = state.isSchemeDropDownOpen,
                    onDismissRequest = {
                        onAction(AddCourseAction.SchemeDropDownVisibilityChanged(false))
                        focusManager.clearFocus()
                    }
                ) {
                    state.schemeOptions?.forEach { scheme ->
                        DropdownMenuItem(
                            text = { Text(scheme.name) },
                            onClick = {
                                onAction(AddCourseAction.SchemeChanged(scheme))
                                onAction(AddCourseAction.SchemeDropDownVisibilityChanged(false))
                                focusManager.clearFocus()
                            }
                        )
                    }
                }
            }
        }

        PresencifyButton(
            onClick = { onAction(AddCourseAction.SubmitClicked) },
            text = if (state.courseId == null) "Add Course" else "Update Course",
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PresencifyAdminTheme {
        AddCourseScreen(
            state = AddCourseState(),
            onAction = {}
        )
    }
}