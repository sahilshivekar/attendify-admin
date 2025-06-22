package com.attendify_admin.home.feature_users.presentation.add_student.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademicDetailsContainer(
    modifier: Modifier = Modifier,
    onEvent: (AddStudentEvent) -> Unit,
    state: AddStudentState,
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
    ) {
        Text(
            text = if(state.studentId == null)  "Fill student's academic details" else "Edit student's academic details",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.Start)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = UiConstants.MAX_WIDTH),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp),
                expanded = state.isAdmissionYearDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.AdmissionYearDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {

                AttendifyTextField(
                    value = state.admissionYear,
                    onValueChange = { onEvent(AddStudentEvent.AdmissionYearChanged(it)) },
                    label = "Admission Year",
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isAdmissionYearDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isAdmissionYearError,
                    isError = state.isAdmissionYearError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isAdmissionYearDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.AdmissionYearDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.heightIn(max = 160.dp),
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.admissionYearOptions.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = {
                                onEvent(AddStudentEvent.AdmissionYearChanged(it))
                                onEvent(AddStudentEvent.AdmissionYearDropDownVisibilityChanged(false))
                                localFocusManager.clearFocus()
                            }

                        )
                    }
                }

            }

            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                expanded = state.isAdmissionTypeDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.AdmissionTypeDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {
                AttendifyTextField(
                    value = state.admissionType.displayName,
                    onValueChange = { onEvent(AddStudentEvent.AdmissionTypeChanged(it)) },
                    label = "Admission Type",
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isAdmissionTypeDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isAdmissionTypeError,
                    isError = state.isAdmissionTypeError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isAdmissionTypeDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.AdmissionTypeDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.admissionTypeOptions.forEach { admissionType ->
                        DropdownMenuItem(
                            text = { Text(text = admissionType.displayName) },
                            onClick = {
                                onEvent(AddStudentEvent.AdmissionTypeChanged(admissionType.displayName))
                                onEvent(AddStudentEvent.AdmissionTypeDropDownVisibilityChanged(false))
                                localFocusManager.clearFocus()
                            }
                        )
                    }
                }

            }
        }




        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = UiConstants.MAX_WIDTH),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp),
                expanded = state.isBranchDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.BranchDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {

                AttendifyTextField(
                    value = state.selectedBranch?.abbreviation ?: "",
                    onValueChange = { },
                    label = "Branch",
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isBranchDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isBranchError,
                    isError = state.isBranchError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isBranchDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.BranchDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.heightIn(max = 160.dp),
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.branchOptions?.forEach { branch ->
                        DropdownMenuItem(
                            text = {
                                Text(text = branch.name)
                            },
                            onClick = {
                                onEvent(AddStudentEvent.BranchChanged(newBranch = branch))
                                onEvent(
                                    AddStudentEvent.BranchDropDownVisibilityChanged(
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
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                expanded = state.isSchemeDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.SchemeDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {

                AttendifyTextField(
                    value = state.selectedScheme?.name ?: "",
                    onValueChange = { },
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
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isSchemeError,
                    isError = state.isSchemeError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isSchemeDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.SchemeDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.heightIn(max = 160.dp),
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.schemeOptions?.forEach { scheme ->
                        DropdownMenuItem(
                            text = {
                                Text(text = scheme.name)
                            },
                            onClick = {
                                onEvent(AddStudentEvent.SchemeChanged(newScheme = scheme))
                                onEvent(
                                    AddStudentEvent.SchemeDropDownVisibilityChanged(
                                        false
                                    )
                                )
                                localFocusManager.clearFocus()
                            }
                        )
                    }
                }
            }
        }

        AttendifyTextField(
            value = state.prn,
            onValueChange = { onEvent(AddStudentEvent.PrnChanged(it)) },
            label = "Permanent Registration Number",
            enabled = !state.isSubmitting && !state.isSubmitted,
            supportingText = state.isPRNError ,
            isError = state.isPRNError != null
        )
    }

}