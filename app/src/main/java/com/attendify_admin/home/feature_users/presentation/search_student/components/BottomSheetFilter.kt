package com.attendify_admin.home.feature_users.presentation.search_student.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendify_admin.R
import com.attendify_admin.common.domain.model.Batch
import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Division
import com.attendify_admin.common.domain.model.Scheme
import com.attendify_admin.common.domain.model.Semester
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.home.feature_users.presentation.search_student.SearchStudentEvent
import com.attendify_admin.home.feature_users.presentation.search_student.SearchStudentState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ModalBottomSheetForSearchStudentScreen(
    sheetState: SheetState,
    onEvent: (SearchStudentEvent) -> Unit,
    state: SearchStudentState,
) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
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
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (state.areBranchesLoading) {
                    item(key = "branch_loader") {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                                .padding(top = 4.dp)
                        )
                    }
                } else {
                    itemsIndexed(
                        state.branchOptions ?: emptyList(),
                        key = { index, _ -> index }) { _, branch ->
                        val isSelected = state.selectedBranches.contains(branch)
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                if (isSelected) {
                                    onEvent(SearchStudentEvent.BranchRemoved(branch))
                                } else {
                                    onEvent(SearchStudentEvent.BranchAdded(branch))
                                }
                            },
                            label = { Text(branch.abbreviation) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                            )
                        )
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
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(state.semesterOptions, key = { index, _ -> index }) { _, semester ->
                    val isSelected = state.selectedSemesters.contains(semester)
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) {
                                onEvent(SearchStudentEvent.SemesterRemoved(semester))
                            } else {
                                onEvent(SearchStudentEvent.SemesterAdded(semester))
                            }
                        },
                        label = {
                            Text("Sem $semester")
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }

        }

        Column {
            // Start Year Section
            Text(
                text = "Academic year of Semester",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(
                    state.academicYearOfSemesterOptions,
                    key = { index, _ -> index }) { _, year ->
                    val isSelected = state.selectedAcademicYearOfSemester == year
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            val newValue = if (isSelected) null else year
                            onEvent(SearchStudentEvent.AcademicYearOfSemesterChanged(newValue))
                        },
                        label = { Text(year) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }
        }


        Column {
            Text(
                text = "Admission Year",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            LazyRow {
                itemsIndexed(
                    state.admissionYearOptions ?: emptyList(),
                    key = { index, _ -> index }) { _, year ->
                    val isSelected = state.selectedAdmissionYear == year
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            val newValue = if (isSelected) null else year
                            onEvent(SearchStudentEvent.AdmissionYearChanged(newValue))
                        },
                        label = { Text(year) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
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
            LazyRow {
                itemsIndexed(
                    state.admissionTypeOptions ?: emptyList(),
                    key = { index, _ -> index }) { _, type ->
                    val isSelected = state.selectedAdmissionTypes.contains(type)

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) {
                                onEvent(SearchStudentEvent.AdmissionTypeRemoved(type))
                            } else {
                                onEvent(SearchStudentEvent.AdmissionTypeAdded(type))
                            }
                        },
                        label = { Text(type) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        modifier = Modifier.padding(end = 12.dp)
                    )
                }
            }

        }

        // Dropout Year option
        Column {
            Text(
                text = "Dropout Year",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            LazyRow {
                itemsIndexed(state.dropoutYearOptions, key = { index, _ -> index }) { _, year ->
                    val isSelected = state.selectedDropoutYear == year

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            val newValue = if (isSelected) null else year
                            onEvent(SearchStudentEvent.DropoutYearChanged(newValue))
                        },
                        label = { Text(year) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

        }
        Column {
            Text("Scheme", style = MaterialTheme.typography.titleMedium)

            if (state.areSchemesLoading == true) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .padding(top = 4.dp)
                )
            }

            LazyRow {
                itemsIndexed(state.schemeOptions, key = { index, _ -> index }) { _, scheme ->
                    val isSelected = state.selectedScheme == scheme

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            onEvent(
                                SearchStudentEvent.SelectedSchemeChanged(
                                    if (isSelected) null else scheme
                                )
                            )
                        },
                        label = { Text(scheme.name) },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        ),
                    )
                }
            }

        }


        Column {
            Text("Division", style = MaterialTheme.typography.titleMedium)


            if (state.areDivisionsLoading == true) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .padding(top = 4.dp)
                )
            } else if (state.divisionOptions.isEmpty() && state.selectedSemesters.isEmpty() && state.selectedBranches.isEmpty() && state.selectedAcademicYearOfSemester == null) {
                Text(
                    "Select one semester, branch and academic year to fetch divisions",
                    fontSize = 12.sp
                )
            } else if (state.divisionOptions.isEmpty()) {
                Text(
                    "No division is there for selected branch, semester and academic year",
                    fontSize = 12.sp
                )
            }

            LazyRow {
                itemsIndexed(state.divisionOptions, key = { index, _ -> index }) { _, division ->
                    val isSelected = state.selectedDivision == division

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            onEvent(
                                SearchStudentEvent.SelectedDivisionChanged(
                                    if (isSelected) null else division
                                )
                            )
                        },
                        label = {
                            Text(text = division.divisionCode)
//                                text = "${division.divisionCode} | Sem ${division.semester?.semesterNumber} | ${division.semester?.academicStartYear}-${division.semester?.academicEndYear}",
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        ),
                    )
                }
            }

        }

        Column {
            Text("Batch", style = MaterialTheme.typography.titleMedium)


            if (state.areBatchesLoading == true) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .padding(top = 4.dp)
                )
            } else if (state.batchOptions.isEmpty() && state.selectedSemesters.isEmpty() && state.selectedBranches.isEmpty() && state.selectedAcademicYearOfSemester == null) {
                Text(
                    "Select one semester, branch and academic year to fetch batches",
                    fontSize = 12.sp
                )
            } else if (state.batchOptions.isEmpty()) {
                Text(
                    "No division is there for selected branch, semester and academic year",
                    fontSize = 12.sp
                )
            }

            LazyRow {
                itemsIndexed(state.batchOptions, key = { index, _ -> index }) { _, batch ->
                    val isSelected = state.selectedBatch == batch

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            onEvent(
                                SearchStudentEvent.SelectedBatchChanged(
                                    if (isSelected) null else batch
                                )
                            )
                        },
                        label = {
                            Text(text = batch.batchCode)
//                                text = "${batch.batchCode} | Sem ${batch.division?.semester?.semesterNumber} | ${batch.division?.semester?.academicStartYear}-${batch.division?.semester?.academicEndYear}",
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        ),
                    )
                }
            }

        }




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


//@Composable
//fun BatchDivision(
//    state: SearchStudentState,
//    onEvent: (SearchStudentEvent) -> Unit,
//) {
//    Column {
//
//        Column {
//            Text("Division", style = MaterialTheme.typography.titleMedium)
//
//
//            if (state.areDivisionsLoading == true) {
//                CircularProgressIndicator(
//                    strokeWidth = 2.dp,
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier
//                        .width(20.dp)
//                        .height(20.dp)
//                        .padding(top = 4.dp)
//                )
//            } else if (state.divisionOptions.isEmpty() && state.selectedSemesters.isEmpty() && state.selectedBranches.isEmpty() && state.selectedAcademicYearOfSemester == null) {
//                Text(
//                    "Select one semester, branch and academic year to fetch divisions",
//                    fontSize = 12.sp
//                )
//            } else if (state.divisionOptions.isEmpty()) {
//                Text(
//                    "No division is there for selected branch, semester and academic year",
//                    fontSize = 12.sp
//                )
//            }
//
//            LazyRow {
//                itemsIndexed(state.divisionOptions, key = { index, _ -> index }) { _, division ->
//                    val isSelected = state.selectedDivision == division
//
//                    FilterChip(
//                        selected = isSelected,
//                        onClick = {
//                            onEvent(
//                                SearchStudentEvent.SelectedDivisionChanged(
//                                    if (isSelected) null else division
//                                )
//                            )
//                        },
//                        label = {
//                            Text(text = division.divisionCode)
////                                text = "${division.divisionCode} | Sem ${division.semester?.semesterNumber} | ${division.semester?.academicStartYear}-${division.semester?.academicEndYear}",
//                        },
//                        modifier = Modifier.padding(end = 8.dp),
//                        colors = FilterChipDefaults.filterChipColors(
//                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
//                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
//                        ),
//                    )
//                }
//            }
//
//        }
//
//        Column {
//            Text("Batch", style = MaterialTheme.typography.titleMedium)
//
//
//            if (state.areBatchesLoading == true) {
//                CircularProgressIndicator(
//                    strokeWidth = 2.dp,
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier
//                        .width(20.dp)
//                        .height(20.dp)
//                        .padding(top = 4.dp)
//                )
//            } else if (state.batchOptions.isEmpty() && state.selectedSemesters.isEmpty() && state.selectedBranches.isEmpty() && state.selectedAcademicYearOfSemester == null) {
//                Text(
//                    "Select one semester, branch and academic year to fetch batches",
//                    fontSize = 12.sp
//                )
//            } else if (state.batchOptions.isEmpty()) {
//                Text(
//                    "No division is there for selected branch, semester and academic year",
//                    fontSize = 12.sp
//                )
//            }
//
//            LazyRow {
//                itemsIndexed(state.batchOptions, key = { index, _ -> index }) { _, batch ->
//                    val isSelected = state.selectedBatch == batch
//
//                    FilterChip(
//                        selected = isSelected,
//                        onClick = {
//                            onEvent(
//                                SearchStudentEvent.SelectedBatchChanged(
//                                    if (isSelected) null else batch
//                                )
//                            )
//                        },
//                        label = {
//                            Text(text = batch.batchCode)
////                                text = "${batch.batchCode} | Sem ${batch.division?.semester?.semesterNumber} | ${batch.division?.semester?.academicStartYear}-${batch.division?.semester?.academicEndYear}",
//                        },
//                        modifier = Modifier.padding(end = 8.dp),
//                        colors = FilterChipDefaults.filterChipColors(
//                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
//                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
//                        ),
//                    )
//                }
//            }
//
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun BatchDivisionPreview() {
//    PreviewWrapper {
//        BatchDivision(
//            state = SearchStudentState(
//                divisionOptions = persistentListOf(
//                    Division(
//                        id = 1,
//                        divisionCode = "A",
//                        semesterId = 1,
//                        semester = null,
//                        batch = null
//                    ),
//                    Division(
//                        id = 1,
//                        divisionCode = "A",
//                        semesterId = 1,
//                        semester = null,
//                        batch = null
//                    )
//                ),
//                batchOptions = persistentListOf(
//                    Batch(
//                        id = 1,
//                        batchCode = "A",
//                        divisionId = 1,
//                        division = null,
//                    ),
//                    Batch(
//                        id = 1,
//                        batchCode = "A",
//                        divisionId = 1,
//                        division = null,
//                    )
//                )
//            ),
//            onEvent = {}
//        )
//    }
//}
