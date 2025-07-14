package com.attendify_admin.home.feature_academics.presentation.add_semester

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import com.attendify_admin.common.utils.DateTimeUtil
import com.attendify_admin.ui.theme.AttendifyAdminTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddSemesterRoot(
    viewModel: AddSemesterViewModel,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddSemesterScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is AddSemesterAction.OnAddUpdateSuccessNavigation -> navController.navigateUp()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSemesterScreen(
    modifier: Modifier = Modifier,
    state: AddSemesterState,
    onAction: (AddSemesterAction) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current

    val startDatePickerState = rememberDatePickerState()
    val endDatePickerState = rememberDatePickerState()

    LaunchedEffect(state.isSubmitted) {
        if (state.isSubmitted) {
            onAction(AddSemesterAction.OnAddUpdateSuccessNavigation)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) { detectTapGestures { localFocusManager.clearFocus() } },
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = state.isAcademicYearDropdownOpen,
            onExpandedChange = {
                onAction(AddSemesterAction.AcademicYearDropdownVisibilityChanged(it))
            }
        ) {
            AttendifyTextField(
                value = state.selectedAcademicYear,
                onValueChange = {},
                label = "Academic Year",
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = state.isAcademicYearDropdownOpen
                    )
                },
                isError = state.academicYearError != null,
                supportingText = state.academicYearError
            )

            ExposedDropdownMenu(
                expanded = true,
                onDismissRequest = {
                    onAction(AddSemesterAction.AcademicYearDropdownVisibilityChanged(false))
                    localFocusManager.clearFocus()
                }
            ) {
                state.academicYearOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onAction(AddSemesterAction.AcademicYearChanged(option))
                            onAction(
                                AddSemesterAction.AcademicYearDropdownVisibilityChanged(
                                    false
                                )
                            )
                            localFocusManager.clearFocus()
                        }
                    )
                }
            }
        }


        ExposedDropdownMenuBox(
            expanded = state.isSemesterNumberDropdownOpen,
            onExpandedChange = {
                onAction(AddSemesterAction.SemesterNumberDropdownVisibilityChanged(it))
            }
        ) {
            AttendifyTextField(
                value = state.selectedSemesterNumber?.toString() ?: "",
                onValueChange = {},
                label = "Semester Number",
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = state.isSemesterNumberDropdownOpen)
                },
                isError = state.semesterNumberError != null,
                supportingText = state.semesterNumberError
            )
            ExposedDropdownMenu(
                expanded = state.isSemesterNumberDropdownOpen,
                onDismissRequest = {
                    onAction(AddSemesterAction.SemesterNumberDropdownVisibilityChanged(false))
                    localFocusManager.clearFocus()
                }
            ) {
                state.semesterNumberOptions.forEach { semester ->
                    DropdownMenuItem(
                        text = { Text(semester.toString()) },
                        onClick = {
                            onAction(AddSemesterAction.SemesterNumberChanged(semester))
                            onAction(AddSemesterAction.SemesterNumberDropdownVisibilityChanged(false))
                            localFocusManager.clearFocus()
                        }
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            expanded = state.isBranchDropdownOpen,
            onExpandedChange = {
                onAction(AddSemesterAction.BranchDropdownVisibilityChanged(it))
            }
        ) {
            AttendifyTextField(
                value = state.selectedBranch?.name ?: "",
                onValueChange = {},
                label = "Branch",
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = state.isBranchDropdownOpen)
                },
                isError = state.branchError != null,
                supportingText = state.branchError
            )
            ExposedDropdownMenu(
                expanded = state.isBranchDropdownOpen,
                onDismissRequest = {
                    onAction(AddSemesterAction.BranchDropdownVisibilityChanged(false))
                    localFocusManager.clearFocus()
                }
            ) {
                state.branchOptions.forEach { branch ->
                    DropdownMenuItem(
                        text = { Text(branch.name) },
                        onClick = {
                            onAction(AddSemesterAction.BranchChanged(branch))
                            onAction(AddSemesterAction.BranchDropdownVisibilityChanged(false))
                            localFocusManager.clearFocus()
                        }
                    )
                }
            }
        }

        AttendifyTextField(
            value = state.startDate?.let { DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(it) } ?: "",
            onValueChange = {},
            label = "Start Date",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    onAction(
                        AddSemesterAction.ShowStartDatePicker
                    )
                }) {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                }
            },
            isError = state.startDateError != null,
            supportingText = state.startDateError
        )

        AttendifyTextField(
            value = state.endDate?.let { DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(it) } ?: "",
            onValueChange = {},
            label = "End Date",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    onAction(
                        AddSemesterAction.ShowEndDatePicker
                    )
                }) {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                }
            },
            isError = state.endDateError != null,
            supportingText = state.endDateError
        )

        AttendifyButton(
            onClick = { onAction(AddSemesterAction.SubmitClicked) },
            text = if (state.semesterId == null) "Add Semester" else "Update Semester",
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (state.isStartDatePickerVisible) {
        DatePickerDialog(
            onDismissRequest = {
                onAction(AddSemesterAction.HideStartDatePicker)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onAction(AddSemesterAction.HideStartDatePicker)
                        startDatePickerState.selectedDateMillis?.let {
                            onAction(
                                AddSemesterAction.StartDateChanged(
                                    DateTimeUtil.datePickerMillisToYYYYMMDD(
                                        it
                                    )
                                )
                            )
                        }
                    }
                ) { Text("Select") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onAction(AddSemesterAction.HideStartDatePicker)
                    }
                ) { Text("Dismiss") }
            }
        ) {
            DatePicker(state = startDatePickerState)
        }
    }

    if (state.isEndDatePickerVisible) {
        DatePickerDialog(
            onDismissRequest = {
                onAction(AddSemesterAction.HideEndDatePicker)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onAction(AddSemesterAction.HideEndDatePicker)
                        endDatePickerState.selectedDateMillis?.let {
                            onAction(
                                AddSemesterAction.EndDateChanged(
                                    DateTimeUtil.datePickerMillisToYYYYMMDD(
                                        it
                                    )
                                )
                            )
                        }
                    }
                ) { Text("Select") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onAction(AddSemesterAction.HideEndDatePicker)
                    }
                ) { Text("Dismiss") }
            }
        ) {
            DatePicker(state = endDatePickerState)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Preview() {
    AttendifyAdminTheme {
        AddSemesterScreen(
            state = AddSemesterState(),
            onAction = {}
        )
    }
}