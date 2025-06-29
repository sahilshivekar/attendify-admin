package com.attendify_admin.home.feature_users.presentation.modify_student_division.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyDropDownMenuBox
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionEvent
import com.attendify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCurrentDivision(
    modifier: Modifier = Modifier,
    state: ModifyStudentDivisionState,
    onEvent: (ModifyStudentDivisionEvent) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
    ) {
//        if (state.selectedCurrentDivision == null) {

        Text(
            text = "Fill out following options to find current division of students",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        // Semester Dropdown
        AttendifyDropDownMenuBox(
            expanded = state.isSemesterDropdownExpandedForCurrentDivision,
            onDropDownVisibilityChanged = {
                onEvent(
                    ModifyStudentDivisionEvent.SemesterDropdownVisibilityChangedForCurrentDivision(
                        it
                    )
                )
            },
            value = state.selectedSemesterNumberForCurrentDivision?.toString() ?: "",
            enabled = true,
            options = state.semesterNumberOptions,
            onSelectItem = {
                onEvent(ModifyStudentDivisionEvent.SemesterNumberSelectedForCurrentDivision(it))
            },
            label = "Semester"
        )


        // Academic Year Dropdown
        AttendifyDropDownMenuBox(
            expanded = state.isAcademicYearDropdownExpandedForCurrentDivision,
            onDropDownVisibilityChanged = {
                onEvent(
                    ModifyStudentDivisionEvent.AcademicYearDropdownVisibilityChangedForCurrentDivision(
                        it
                    )
                )
            },
            value = state.selectedAcademicYearForCurrentDivision ?: "",
            enabled = true,
            options = state.academicYearOfSemesterOptions,
            onSelectItem = {
                onEvent(ModifyStudentDivisionEvent.AcademicYearSelectedForCurrentDivision(it))
            },
            label = "Academic Year"
        )

        ExposedDropdownMenuBox(
            expanded = state.isBranchDropdownExpandedForCurrentDivision,
            onExpandedChange = {
                onEvent(
                    ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForCurrentDivision(
                        it
                    )
                )
            },
            modifier = modifier
        ) {
            AttendifyTextField(
                value = state.selectedBranchForCurrentDivision?.abbreviation ?: "",
                onValueChange = {},
                label = "Branch",
                readOnly = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = state.isBranchDropdownExpandedForCurrentDivision,
                        modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = state.isBranchDropdownExpandedForCurrentDivision,
                onDismissRequest = {
                    onEvent(
                        ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForCurrentDivision(
                            false
                        )
                    )
                    localFocusManager.clearFocus()
                },
                shape = MaterialTheme.shapes.medium,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                if (state.branchOptions.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .size(24.dp)
                        )
                    }
                }
                state.branchOptions.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onEvent(
                                ModifyStudentDivisionEvent.BranchSelectedForCurrentDivision(
                                    option
                                )
                            )
                            onEvent(
                                ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForCurrentDivision(
                                    false
                                )
                            )
                            localFocusManager.clearFocus()

                        },
                        text = {
                            Text(
                                text = option.abbreviation,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        AttendifyButton(
            onClick = { onEvent(ModifyStudentDivisionEvent.FetchMatchingDivisionsForCurrentDivision) },
            enabled = !state.areDivisionsLoadingForCurrentDivision,
            isLoading = state.areDivisionsLoadingForCurrentDivision
        ) {
            Text("Find Divisions")
        }

        Spacer(Modifier.height(24.dp))

        if (state.foundDivisionsForCurrentDivision.isNotEmpty()) {
            Text("Select one division", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            LazyColumn {
                items(
                    items = state.foundDivisionsForCurrentDivision,
                    key = {
                        it.id
                    }
                ) { division ->
                    ListItem(
                        headlineContent = {
                            Text("Division ${division.divisionCode} | Sem ${division.semester?.semesterNumber ?: "N/A"}")
                        },
                        supportingContent = {
                            Text("Branch: ${division.semester?.branch?.abbreviation ?: "N/A"}, ${division.semester?.academicStartYear} - ${division.semester?.academicEndYear}")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onEvent(ModifyStudentDivisionEvent.CurrentDivisionSelected(division))
                            }
                            .clip(MaterialTheme.shapes.medium),
                        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}