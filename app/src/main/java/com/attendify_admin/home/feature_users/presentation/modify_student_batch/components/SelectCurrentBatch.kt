package com.attendify_admin.home.feature_users.presentation.modify_student_batch.components

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
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.ModifyStudentBatchEvent
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.ModifyStudentBatchState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCurrentBatch(
    modifier: Modifier = Modifier,
    state: ModifyStudentBatchState,
    onEvent: (ModifyStudentBatchEvent) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
    ) {
//        if (state.selectedCurrentBatch == null) {

        Text(
            text = "Fill out following options to find current batch of students",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        // Semester Dropdown
        AttendifyDropDownMenuBox(
            expanded = state.isSemesterDropdownExpandedForCurrentBatch,
            onDropDownVisibilityChanged = {
                onEvent(
                    ModifyStudentBatchEvent.SemesterDropdownVisibilityChangedForCurrentBatch(
                        it
                    )
                )
            },
            value = state.selectedSemesterNumberForCurrentBatch?.toString() ?: "",
            enabled = true,
            options = state.semesterNumberOptions,
            onSelectItem = {
                onEvent(ModifyStudentBatchEvent.SemesterNumberSelectedForCurrentBatch(it))
            },
            label = "Semester"
        )


        // Academic Year Dropdown
        AttendifyDropDownMenuBox(
            expanded = state.isAcademicYearDropdownExpandedForCurrentBatch,
            onDropDownVisibilityChanged = {
                onEvent(
                    ModifyStudentBatchEvent.AcademicYearDropdownVisibilityChangedForCurrentBatch(
                        it
                    )
                )
            },
            value = state.selectedAcademicYearForCurrentBatch ?: "",
            enabled = true,
            options = state.academicYearOfSemesterOptions,
            onSelectItem = {
                onEvent(ModifyStudentBatchEvent.AcademicYearSelectedForCurrentBatch(it))
            },
            label = "Academic Year"
        )

        ExposedDropdownMenuBox(
            expanded = state.isBranchDropdownExpandedForCurrentBatch,
            onExpandedChange = {
                onEvent(
                    ModifyStudentBatchEvent.BranchDropdownVisibilityChangedForCurrentBatch(
                        it
                    )
                )
            },
            modifier = modifier
        ) {
            AttendifyTextField(
                value = state.selectedBranchForCurrentBatch?.abbreviation ?: "",
                onValueChange = {},
                label = "Branch",
                readOnly = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = state.isBranchDropdownExpandedForCurrentBatch,
                        modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = state.isBranchDropdownExpandedForCurrentBatch,
                onDismissRequest = {
                    onEvent(
                        ModifyStudentBatchEvent.BranchDropdownVisibilityChangedForCurrentBatch(
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
                                ModifyStudentBatchEvent.BranchSelectedForCurrentBatch(
                                    option
                                )
                            )
                            onEvent(
                                ModifyStudentBatchEvent.BranchDropdownVisibilityChangedForCurrentBatch(
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
            onClick = { onEvent(ModifyStudentBatchEvent.FetchMatchingBatchesForCurrentBatch) },
            enabled = !state.areBatchesLoadingForCurrentBatch,
            isLoading = state.areBatchesLoadingForCurrentBatch
        ) {
            Text("Find Batches")
        }

        Spacer(Modifier.height(24.dp))

        if (state.foundBatchesForCurrentBatch.isNotEmpty()) {
            Text("Select one batch", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            LazyColumn {
                items(
                    items = state.foundBatchesForCurrentBatch,
                    key = {
                        it.id
                    }
                ) { batch ->
                    ListItem(
                        headlineContent = {
                            Text("Batch ${batch.batchCode} | Sem ${batch.division?.semester?.semesterNumber ?: "N/A"}")
                        },
                        supportingContent = {
                            Text("Branch: ${batch.division?.semester?.branch?.abbreviation ?: "N/A"}, ${batch.division?.semester?.academicStartYear} - ${batch.division?.semester?.academicEndYear}")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onEvent(ModifyStudentBatchEvent.CurrentBatchSelected(batch))
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