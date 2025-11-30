package com.presencify_admin.home.feature_users.presentation.assign_student_to_semester

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyDropDownMenuBox
import com.presencify_admin.common.presentation.components.PresencifyTextField
import com.presencify_admin.home.feature_users.presentation.assign_student_to_semester.components.StudentCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignStudentSemesterScreen(
    modifier: Modifier = Modifier,
    state: AssignStudentSemesterState,
    onEvent: (AssignStudentSemesterEvent) -> Unit,
    onSelectStudents: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val localFocusManager = LocalFocusManager.current
        if (state.selectedSemester == null) {
            Text(
                text = "Fill out following options to find desired semesters",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Column(
                modifier = Modifier
                    .widthIn(max = UiConstants.MAX_WIDTH)
            ) {

                // Semester Dropdown
                PresencifyDropDownMenuBox(
                    expanded = state.isSemesterDropdownExpanded,
                    onDropDownVisibilityChanged = {
                        onEvent(AssignStudentSemesterEvent.SemesterDropdownVisibilityChanged(it))
                    },
                    value = state.selectedSemesterNumber?.toString() ?: "",
                    enabled = true,
                    options = state.semesterNumberOptions,
                    onSelectItem = {
                        onEvent(AssignStudentSemesterEvent.SemesterNumberSelected(it))
                    },
                    label = "Semester"
                )


                // Academic Year Dropdown
                PresencifyDropDownMenuBox(
                    expanded = state.isAcademicYearDropdownExpanded,
                    onDropDownVisibilityChanged = {
                        onEvent(AssignStudentSemesterEvent.AcademicYearDropdownVisibilityChanged(it))
                    },
                    value = state.selectedAcademicYear ?: "",
                    enabled = true,
                    options = state.academicYearOfSemesterOptions,
                    onSelectItem = {
                        onEvent(AssignStudentSemesterEvent.AcademicYearSelected(it))
                    },
                    label = "Academic Year"
                )

                ExposedDropdownMenuBox(
                    expanded = state.isBranchDropdownExpanded,
                    onExpandedChange = {
                        onEvent(
                            AssignStudentSemesterEvent.BranchDropdownVisibilityChanged(
                                it
                            )
                        )
                    },
                    modifier = modifier
                ) {
                    PresencifyTextField(
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
                            onEvent(AssignStudentSemesterEvent.BranchDropdownVisibilityChanged(false))
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
                                    onEvent(AssignStudentSemesterEvent.BranchSelected(option))
                                    onEvent(
                                        AssignStudentSemesterEvent.BranchDropdownVisibilityChanged(
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

            PresencifyButton(
                onClick = { onEvent(AssignStudentSemesterEvent.FetchMatchingSemesters) },
                enabled = !state.areSemestersLoading,
                isLoading = state.areSemestersLoading
            ) {
                Text("Find Semesters")
            }

            Spacer(Modifier.height(24.dp))

            if (state.foundSemesters.isNotEmpty()) {
                Text("Select one semester", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))

                LazyColumn {
                    items(
                        items = state.foundSemesters,
                        key = {
                            it.id
                        }
                    ) { semester ->
                        ListItem(
                            headlineContent = {
                                Text("Semester ${semester.semesterNumber}")
                            },
                            supportingContent = {
                                Text("Branch: ${semester.branch?.abbreviation ?: "N/A"}, ${semester.academicStartYear} - ${semester.academicEndYear}")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onEvent(AssignStudentSemesterEvent.SemesterSelected(semester))
                                }
                                .clip(MaterialTheme.shapes.medium),
                            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
                        )
                    }
                }
            }


        } else {
            Text("Selected Semester", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            ListItem(
                headlineContent = {
                    Text("Semester ${state.selectedSemester.semesterNumber}")
                },
                supportingContent = {
                    Text("Branch: ${state.selectedSemester.branch?.abbreviation ?: "N/A"}, ${state.selectedSemester.academicStartYear} - ${state.selectedSemester.academicEndYear}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
            )

            Spacer(Modifier.height(24.dp))
            if (state.selectedStudentIds.isEmpty()) {
                PresencifyButton(
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
                        onCancel = { onEvent(AssignStudentSemesterEvent.RemoveStudentClicked(student.id)) },
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
                PresencifyButton(
                    onClick = { onEvent(AssignStudentSemesterEvent.AssignStudentsClicked) },
                    enabled = !state.isAssigningStudents,
                ) {
                    Text("Assign Students")
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AssignStudentSemesterScreenPreview() {
    PreviewWrapper {
        AssignStudentSemesterScreen(
            state = AssignStudentSemesterState(),
            onEvent = {},
            onSelectStudents = {}
        )
    }
}
