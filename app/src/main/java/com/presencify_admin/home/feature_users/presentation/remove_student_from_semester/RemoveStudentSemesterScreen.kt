package com.presencify_admin.home.feature_users.presentation.remove_student_from_semester

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyDropDownMenuBox
import com.presencify_admin.common.presentation.components.PresencifyTextField
import com.presencify_admin.home.feature_users.presentation.remove_student_from_semester.components.StudentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoveStudentSemesterScreen(
    modifier: Modifier = Modifier,
    state: RemoveStudentSemesterState,
    onEvent: (RemoveStudentSemesterEvent) -> Unit,
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
                        onEvent(RemoveStudentSemesterEvent.SemesterDropdownVisibilityChanged(it))
                    },
                    value = state.selectedSemesterNumber?.toString() ?: "",
                    enabled = true,
                    options = state.semesterNumberOptions,
                    onSelectItem = {
                        onEvent(RemoveStudentSemesterEvent.SemesterNumberSelected(it))
                    },
                    label = "Semester"
                )


                // Academic Year Dropdown
                PresencifyDropDownMenuBox(
                    expanded = state.isAcademicYearDropdownExpanded,
                    onDropDownVisibilityChanged = {
                        onEvent(RemoveStudentSemesterEvent.AcademicYearDropdownVisibilityChanged(it))
                    },
                    value = state.selectedAcademicYear ?: "",
                    enabled = true,
                    options = state.academicYearOfSemesterOptions,
                    onSelectItem = {
                        onEvent(RemoveStudentSemesterEvent.AcademicYearSelected(it))
                    },
                    label = "Academic Year"
                )

                ExposedDropdownMenuBox(
                    expanded = state.isBranchDropdownExpanded,
                    onExpandedChange = {
                        onEvent(
                            RemoveStudentSemesterEvent.BranchDropdownVisibilityChanged(
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
                            onEvent(RemoveStudentSemesterEvent.BranchDropdownVisibilityChanged(false))
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
                                    onEvent(RemoveStudentSemesterEvent.BranchSelected(option))
                                    onEvent(
                                        RemoveStudentSemesterEvent.BranchDropdownVisibilityChanged(
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
                onClick = { onEvent(RemoveStudentSemesterEvent.FetchMatchingSemesters) },
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
                                    onEvent(RemoveStudentSemesterEvent.SemesterSelected(semester))
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
        }
        if (state.students.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(
                "Students assigned to this semester",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = UiConstants.MAX_WIDTH),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = state.students,
                    key = {
                        it.id
                    }
                ) { student ->
                    StudentCard(
                        studentName = student.studentName,
                        studentImageUrl = student.studentImageUrl,
                        onUnassignClicked = { studentSemesterId ->
                            onEvent(
                                RemoveStudentSemesterEvent.UnassignStudentClicked(
                                    studentSemesterId
                                )
                            )
                        },
                        isUnassigning = student.isUnassigning,
                        isUnassigned = student.isUnassigned,
                        isFailedToUnassign = student.isFailedToUnassign,
                        studentSemesterId = student.studentSemesterId,
                        studentId = student.id
                    )
                    Spacer(Modifier.height(8.dp))
                }
                item {
                    if (state.areStudentsLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

    }
}
