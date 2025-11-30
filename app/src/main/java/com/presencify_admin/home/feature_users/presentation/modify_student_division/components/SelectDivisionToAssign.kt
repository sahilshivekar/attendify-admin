package com.presencify_admin.home.feature_users.presentation.modify_student_division.components

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
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyDropDownMenuBox
import com.presencify_admin.common.presentation.components.PresencifyTextField
import com.presencify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionEvent
import com.presencify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDivisionToAssign(
    modifier: Modifier = Modifier,
    state: ModifyStudentDivisionState,
    onEvent: (ModifyStudentDivisionEvent) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
    ) {
//        if (state.selectedNewDivision == null) {

        Text(
            text = "Fill out following options to find new division to assign",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        // Semester Dropdown
        PresencifyDropDownMenuBox(
            expanded = state.isSemesterDropdownExpandedForNewDivision,
            onDropDownVisibilityChanged = {
                onEvent(
                    ModifyStudentDivisionEvent.SemesterDropdownVisibilityChangedForNewDivision(
                        it
                    )
                )
            },
            value = state.selectedSemesterNumberForNewDivision?.toString() ?: "",
            enabled = true,
            options = state.semesterNumberOptions,
            onSelectItem = {
                onEvent(ModifyStudentDivisionEvent.SemesterNumberSelectedForNewDivision(it))
            },
            label = "Semester"
        )


        // Academic Year Dropdown
        PresencifyDropDownMenuBox(
            expanded = state.isAcademicYearDropdownExpandedForNewDivision,
            onDropDownVisibilityChanged = {
                onEvent(
                    ModifyStudentDivisionEvent.AcademicYearDropdownVisibilityChangedForNewDivision(
                        it
                    )
                )
            },
            value = state.selectedAcademicYearForNewDivision ?: "",
            enabled = true,
            options = state.academicYearOfSemesterOptions,
            onSelectItem = {
                onEvent(ModifyStudentDivisionEvent.AcademicYearSelectedForNewDivision(it))
            },
            label = "Academic Year"
        )

        ExposedDropdownMenuBox(
            expanded = state.isBranchDropdownExpandedForNewDivision,
            onExpandedChange = {
                onEvent(
                    ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForNewDivision(
                        it
                    )
                )
            },
            modifier = modifier
        ) {
            PresencifyTextField(
                value = state.selectedBranchForNewDivision?.abbreviation ?: "",
                onValueChange = {},
                label = "Branch",
                readOnly = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = state.isBranchDropdownExpandedForNewDivision,
                        modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = state.isBranchDropdownExpandedForNewDivision,
                onDismissRequest = {
                    onEvent(
                        ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForNewDivision(
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
                            onEvent(ModifyStudentDivisionEvent.BranchSelectedForNewDivision(option))
                            onEvent(
                                ModifyStudentDivisionEvent.BranchDropdownVisibilityChangedForNewDivision(
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

        PresencifyButton(
            onClick = { onEvent(ModifyStudentDivisionEvent.FetchMatchingDivisionsForNewDivision) },
            enabled = !state.areDivisionsLoadingForNewDivision,
            isLoading = state.areDivisionsLoadingForNewDivision
        ) {
            Text("Find Divisions")
        }

        Spacer(Modifier.height(24.dp))

        if (state.foundDivisionsForNewDivision.isNotEmpty()) {
            Text("Select one division", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            LazyColumn {
                items(
                    items = state.foundDivisionsForNewDivision,
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
                                onEvent(ModifyStudentDivisionEvent.NewDivisionSelected(division))
                            }
                            .clip(MaterialTheme.shapes.medium),
                        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
//        } else {


    }
//    }
}