package com.attendify_admin.home.feature_users.presentation.assign_student_to_batch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.attendify_admin.home.feature_users.presentation.assign_student_to_batch.components.StudentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignStudentBatchScreen(
    modifier: Modifier = Modifier,
    state: AssignStudentBatchState,
    onEvent: (AssignStudentBatchEvent) -> Unit,
    onSelectStudents: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val localFocusManager = LocalFocusManager.current
        if (state.selectedBatch == null) {
            Text(
                text = "Fill out following options to find desired batches",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Column(
                modifier = Modifier
                    .widthIn(max = UiConstants.MAX_WIDTH)
            ) {

                // semester Dropdown
                AttendifyDropDownMenuBox(
                    expanded = state.isSemesterDropdownExpanded,
                    onDropDownVisibilityChanged = {
                        onEvent(AssignStudentBatchEvent.SemesterDropdownVisibilityChanged(it))
                    },
                    value = state.selectedSemesterNumber?.toString() ?: "",
                    enabled = true,
                    options = state.semesterNumberOptions,
                    onSelectItem = {
                        onEvent(AssignStudentBatchEvent.SemesterNumberSelected(it))
                    },
                    label = "Semester"
                )


                // Academic Year Dropdown
                AttendifyDropDownMenuBox(
                    expanded = state.isAcademicYearDropdownExpanded,
                    onDropDownVisibilityChanged = {
                        onEvent(AssignStudentBatchEvent.AcademicYearDropdownVisibilityChanged(it))
                    },
                    value = state.selectedAcademicYear ?: "",
                    enabled = true,
                    options = state.academicYearOfBatchOptions,
                    onSelectItem = {
                        onEvent(AssignStudentBatchEvent.AcademicYearSelected(it))
                    },
                    label = "Academic Year"
                )

                ExposedDropdownMenuBox(
                    expanded = state.isBranchDropdownExpanded,
                    onExpandedChange = {
                        onEvent(
                            AssignStudentBatchEvent.BranchDropdownVisibilityChanged(
                                it
                            )
                        )
                    },
                    modifier = modifier
                ) {
                    AttendifyTextField(
                        value = state.selectedBranch?.abbreviation ?: "",
                        onValueChange = {},
                        label = "Branch",
                        readOnly = true,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryEditable),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = state.isBranchDropdownExpanded,
                                modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                            )
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = state.isBranchDropdownExpanded,
                        onDismissRequest = {
                            onEvent(AssignStudentBatchEvent.BranchDropdownVisibilityChanged(false))
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
                                    onEvent(AssignStudentBatchEvent.BranchSelected(option))
                                    onEvent(
                                        AssignStudentBatchEvent.BranchDropdownVisibilityChanged(
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
            }

            Spacer(Modifier.height(16.dp))

            AttendifyButton(
                onClick = { onEvent(AssignStudentBatchEvent.FetchMatchingBatches) },
                enabled = !state.areBatchesLoading,
                isLoading = state.areBatchesLoading
            ) {
                Text("Find Batches")
            }

            Spacer(Modifier.height(24.dp))

            if (state.foundBatches.isNotEmpty()) {
                Text("Select one batch", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))

                LazyColumn {
                    items(
                        items = state.foundBatches,
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
                                    onEvent(AssignStudentBatchEvent.BatchSelected(batch))
                                }
                                .clip(MaterialTheme.shapes.medium),
                            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
                        )
                        Spacer(Modifier.height(8.dp))

                    }
                }
            }


        } else {
            Text("Selected Batch", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            ListItem(
                headlineContent = {
                    Text("Batch ${state.selectedBatch.batchCode} | Sem ${state.selectedBatch.division?.semester?.semesterNumber ?: "N/A"}")
                },
                supportingContent = {
                    Text("Branch: ${state.selectedBatch.division?.semester?.branch?.abbreviation ?: "N/A"}, ${state.selectedBatch.division?.semester?.academicStartYear} - ${state.selectedBatch.division?.semester?.academicEndYear}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
            )

            Spacer(Modifier.height(24.dp))
            if (state.selectedStudentIds.isEmpty()) {
                AttendifyButton(
                    onClick = {
                        onSelectStudents()
                    }
                ) {
                    Text("Select Students")
                }
            }
        }
        if (state.selectedStudentIds.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text("Selected students", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = UiConstants.MAX_WIDTH),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = state.selectedStudents,
                    key = {
                        it.id
                    }
                ) { student ->
                    StudentCard(
                        studentName = student.studentName,
                        studentImageUrl = student.studentImageUrl,
                        onCancel = { onEvent(AssignStudentBatchEvent.RemoveStudentClicked(student.id)) },
                        isAdded = student.isAdded,
                        isFailedToAdd = student.isFailedToAdd,
                        supportingText = student.supportingText,
                        isAdding = student.isAdding
                    )
                    Spacer(Modifier.height(8.dp))
                }
                item {
                    if (state.areStudentsLoading) {
                        CircularProgressIndicator()
                        Text("Loading Students...", modifier = Modifier.padding(top = 16.dp))
                    }
                }
            }

            if (state.selectedStudents.isNotEmpty()) {
                AttendifyButton(
                    onClick = { onEvent(AssignStudentBatchEvent.AssignStudentsClicked) },
                    enabled = !state.isAssigningStudents,
                ) {
                    Text("Assign Students")
                }
            }
        }

    }
}