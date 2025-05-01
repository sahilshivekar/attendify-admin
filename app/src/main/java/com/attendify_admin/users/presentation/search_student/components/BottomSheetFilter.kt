package com.attendify_admin.users.presentation.search_student.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.attendify_admin.R
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyFilterOption
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.users.presentation.search_student.SearchStudentEvent
import com.attendify_admin.users.presentation.search_student.SearchStudentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ModalBottomSheetForSearchStudentScreen(
    sheetState: SheetState,
    onEvent: (SearchStudentEvent) -> Unit,
    state: SearchStudentState
) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            coroutineScope.launch {
                sheetState.hide()
            }
            onEvent(SearchStudentEvent.BottomSheetVisibilityChanged(false))
        },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .statusBarsPadding(),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Filter",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Reset",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onEvent(SearchStudentEvent.ResetFilters) }
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(bottom = 28.dp)
                .padding(horizontal = 16.dp),
            thickness = .5.dp,
            color = colorResource(
                R.color.text_field_border_label
            )
        )

        // scrollable column for the all the options
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            //branch option
            Column {
                Text(
                    text = "Branch",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                FlowRow {
                    if (state.areBranchesLoading) {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    } else {
                        state.branchOptions.forEach { branch ->
                            branch?.let {
                                AttendifyFilterOption(
                                    option = branch,
                                    optionText = branch.abbreviation,
                                    onSelect = {
                                        onEvent(SearchStudentEvent.BranchAdded(branch))
                                    },
                                    onUnselect = {
                                        onEvent(SearchStudentEvent.BranchRemoved(branch))
                                    },
                                    isSelected = state.selectedBranches.contains(branch)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                        }
                    }
                }
            }


            //semester option
            Column {
                Text(
                    text = "Semester Number",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(bottom = 8.dp, top = 12.dp)

                )
                FlowRow {
                    state.semesterOptions.forEach { semester ->
                        AttendifyFilterOption(
                            modifier = Modifier.padding(bottom = 12.dp),
                            option = semester,
                            optionText = semester.toString(),
                            onSelect = {
                                onEvent(SearchStudentEvent.SemesterAdded(semester))
                            },
                            onUnselect = {
                                onEvent(SearchStudentEvent.SemesterRemoved(semester))
                            },
                            isSelected = state.selectedSemesters.contains(semester)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }

            //academic start year of semester option
            Column {
                val localFocusManager = LocalFocusManager.current

                Text(
                    text = "Academic Start & End Year of semester",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(horizontalArrangement = Arrangement.SpaceBetween) {

                    ExposedDropdownMenuBox(
                        expanded = state.isAcademicStartYearOfSemesterDropDownVisible,
                        onExpandedChange = {
                            onEvent(
                                SearchStudentEvent.AcademicStartYearOfSemesterDropDownVisibilityChanged(
                                    it
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.5f)
                            .padding(end = 8.dp)
                    ) {
                        AttendifyTextField(
                            value = state.selectedAcademicStartYearOfSemester ?: "",
                            onValueChange = { /* Prevent manual editing */ },
                            label = "Start Year",
                            readOnly = true,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(MenuAnchorType.PrimaryEditable),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = state.isAcademicStartYearOfSemesterDropDownVisible,
                                    modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                                )
                            },
                        )
                        ExposedDropdownMenu(
                            expanded = state.isAcademicStartYearOfSemesterDropDownVisible,
                            onDismissRequest = {
                                onEvent(
                                    SearchStudentEvent.AcademicStartYearOfSemesterDropDownVisibilityChanged(
                                        false
                                    )
                                )
                                localFocusManager.clearFocus()
                            },
                            shape = MaterialTheme.shapes.medium,
                            containerColor = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.heightIn(max = 160.dp)
                        ) {
                            state.academicStartYearOfSemesterOptions.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        onEvent(
                                            SearchStudentEvent.AcademicStartYearOfSemesterChanged(
                                                option
                                            )
                                        )
                                        onEvent(
                                            SearchStudentEvent.AcademicStartYearOfSemesterDropDownVisibilityChanged(
                                                false
                                            )
                                        )
                                        localFocusManager.clearFocus()
                                    },
                                    text = {
                                        Text(
                                            text = option,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                )
                            }
                        }
                    }
                    ExposedDropdownMenuBox(
                        expanded = state.isAcademicEndYearOfSemesterDropDownVisible,
                        onExpandedChange = {
                            onEvent(
                                SearchStudentEvent.AcademicEndYearOfSemesterDropDownVisibilityChanged(
                                    it
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.5f)
                            .padding(start = 8.dp)
                    ) {
                        AttendifyTextField(
                            value = state.selectedAcademicEndYearOfSemester ?: "",
                            onValueChange = { /* Prevent manual editing */ },
                            label = "End Year",
                            readOnly = true,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(MenuAnchorType.PrimaryEditable),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = state.isAcademicEndYearOfSemesterDropDownVisible,
                                    modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                                )
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = state.isAcademicEndYearOfSemesterDropDownVisible,
                            onDismissRequest = {
                                onEvent(
                                    SearchStudentEvent.AcademicEndYearOfSemesterDropDownVisibilityChanged(
                                        false
                                    )
                                )
                                localFocusManager.clearFocus()
                            },
                            shape = MaterialTheme.shapes.medium,
                            containerColor = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.heightIn(max = 160.dp)
                        ) {
                            state.academicEndYearOfSemesterOptions.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        onEvent(
                                            SearchStudentEvent.AcademicEndYearOfSemesterChanged(
                                                option
                                            )
                                        )
                                        onEvent(
                                            SearchStudentEvent.AcademicEndYearOfSemesterDropDownVisibilityChanged(
                                                false
                                            )
                                        )
                                        localFocusManager.clearFocus()

                                    },
                                    text = {
                                        Text(
                                            text = option,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }


            //academic status option
            Column {
                Text(
                    text = "Academic Status",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                FlowRow {
                    state.academicStatusOptions.forEach { status ->
                        status?.let {
                            AttendifyFilterOption(
                                option = status,
                                optionText = status,
                                onSelect = {
                                    onEvent(SearchStudentEvent.AcademicStatusAdded(status))
                                },
                                onUnselect = {
                                    onEvent(SearchStudentEvent.AcademicStatusRemoved(status))
                                },
                                isSelected = state.selectedAcademicStatuses.contains(status)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }

            //admission year option
            Column {

                val localFocusManager = LocalFocusManager.current

                Text(
                    text = "Admission Year",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp, top = 12.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = state.isAdmissionYearDropDownVisible,
                    onExpandedChange = {
                        onEvent(
                            SearchStudentEvent.AdmissionYearDropDownVisibilityChanged(
                                it
                            )
                        )
                    }, // Reusing this event for simplicity
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AttendifyTextField(
                        value = state.selectedAdmissionYear ?: "",
                        onValueChange = { /* Prevent manual editing */ },
//                        label = "Admission Year",
                        readOnly = true,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryEditable),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = state.isAdmissionYearDropDownVisible,
                                modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                            )
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = state.isAdmissionYearDropDownVisible,
                        onDismissRequest = {
                            onEvent(
                                SearchStudentEvent.AdmissionYearDropDownVisibilityChanged(
                                    false
                                )
                            )
                            localFocusManager.clearFocus()
                        },
                        shape = MaterialTheme.shapes.medium,
                        containerColor = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.heightIn(max = 160.dp)
                    ) {
                        state.admissionYearOptions.forEach { option ->
                            option?.let {
                                DropdownMenuItem(
                                    onClick = {
                                        onEvent(
                                            SearchStudentEvent.AdmissionYearChanged(
                                                option
                                            )
                                        ) // Reusing this event for simplicity
                                        onEvent(
                                            SearchStudentEvent.AdmissionYearDropDownVisibilityChanged(
                                                false
                                            )
                                        )
                                        localFocusManager.clearFocus()
                                    },
                                    text = {
                                        Text(
                                            text = option,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            //admission type option
            Column {
                Text(
                    text = "Admission Type",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                FlowRow {
                    state.admissionTypeOptions.forEach { type ->
                        type?.let {
                            AttendifyFilterOption(
                                option = type,
                                optionText = type,
                                onSelect = {
                                    onEvent(SearchStudentEvent.AdmissionTypeAdded(type))
                                },
                                onUnselect = {
                                    onEvent(SearchStudentEvent.AdmissionTypeRemoved(type))
                                },
                                isSelected = state.selectedAdmissionTypes.contains(type)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }

            //scheme option
//            Column {
//                Text(
//                    text = "Scheme",
//                    style = MaterialTheme.typography.titleMedium,
//                    color = MaterialTheme.colorScheme.onBackground,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                FlowRow {
//                    state.schemeOptions.forEach { scheme ->
//                        scheme?.let {
//                            AttendifyFilterOption(
//                                option = scheme,
//                                optionText = scheme.name,
//                                onSelect = {
//                                    onEvent(SearchStudentEvent.SchemeAdded(scheme))
//                                },
//                                onUnselect = {
//                                    onEvent(SearchStudentEvent.SchemeRemoved(scheme))
//                                }
//                            )
//                        }
//                        Spacer(modifier = Modifier.width(12.dp))
//                    }
//                }
//            }

            //division option
//            Column {
//                Text(
//                    text = "Division",
//                    style = MaterialTheme.typography.titleMedium,
//                    color = MaterialTheme.colorScheme.onBackground,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                FlowRow {
//                    state.divisionOptions.forEach { division ->
//                        AttendifyFilterOption(
//                            option = division,
//                            optionText = division,
//                            onSelect = {
//                                onEvent(SearchStudentEvent.DivisionAdded(division))
//                            },
//                            onUnselect = {
//                                onEvent(SearchStudentEvent.DivisionRemoved(division))
//                            }
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(12.dp))
//                }
//            }
//        }

            //batch option
//        Column {
//            Text(
//                text = "Batch",
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.onBackground,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            FlowRow {
//                state.batchOptions.forEach { batch ->
//                    AttendifyFilterOption(
//                        option = batch,
//                        optionText = batch,
//                        onSelect = {
//                            onEvent(SearchStudentEvent.BatchAdded(batch))
//                        },
//                        onUnselect = {
//                            onEvent(SearchStudentEvent.BatchRemoved(batch))
//                        }
//                    )
//                }
//                Spacer(modifier = Modifier.width(12.dp))
//            }
//        }

            AttendifyButton(
                onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                        onEvent(SearchStudentEvent.ApplyFilters)
                    }
                },
                text = "Apply Filters",
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

