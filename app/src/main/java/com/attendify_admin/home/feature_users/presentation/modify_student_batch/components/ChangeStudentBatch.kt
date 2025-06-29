package com.attendify_admin.home.feature_users.presentation.modify_student_batch.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.common.utils.DateTimeUtil
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.ModifyStudentBatchEvent
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.ModifyStudentBatchState


@Composable
fun ChangeStudentBatch(
    modifier: Modifier = Modifier,
    state: ModifyStudentBatchState,
    onEvent: (ModifyStudentBatchEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(max = UiConstants.MAX_WIDTH),
    ) {
        Text(
            "Selected current batch of students",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
        ListItem(
            headlineContent = {
                Text("Batch ${state.selectedCurrentBatch?.batchCode} | Sem ${state.selectedCurrentBatch?.division?.semester?.semesterNumber ?: "N/A"}")
            },
            supportingContent = {
                Text("Branch: ${state.selectedCurrentBatch?.division?.semester?.branch?.abbreviation ?: "N/A"}, ${state.selectedCurrentBatch?.division?.semester?.academicStartYear} - ${state.selectedCurrentBatch?.division?.semester?.academicEndYear}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium),
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Selected new batch to assign",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
        ListItem(
            headlineContent = {
                Text("Batch ${state.selectedNewBatch?.batchCode} | Sem ${state.selectedCurrentBatch?.division?.semester?.semesterNumber ?: "N/A"}")
            },
            supportingContent = {
                Text("Branch: ${state.selectedNewBatch?.division?.semester?.branch?.abbreviation ?: "N/A"}, ${state.selectedNewBatch?.division?.semester?.academicStartYear} - ${state.selectedNewBatch?.division?.semester?.academicEndYear}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium),
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
        )
        Spacer(Modifier.height(8.dp))

        AttendifyTextField(
            value = if (state.newBatchStartDate != null) DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(
                state.newBatchStartDate
            ) else "",
            onValueChange = {},
            label = "New batch start date",
            trailingIcon = {
                IconButton(
                    onClick = {
                        onEvent(ModifyStudentBatchEvent.DatePickerVisibilityChanged)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                }
            },
            readOnly = true,
            maxLines = 1,
        )
        Spacer(Modifier.height(16.dp))

        Text(
            "Students in current batch",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
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
                        studentBatchId = student.studentBatchId,
                        studentId = student.id,
                        isChanged = student.isChanged,
                        isFailedToChange = student.isFailedToChange,
                        isChanging = student.isChanging,
                        supportingText = student.supportingText,
                        state = state,
                        onChangeClicked = { studentBatchId ->
                            onEvent(
                                ModifyStudentBatchEvent.ChangeStudentBatchClicked(
                                    studentBatchId
                                )
                            )
                        },
                        currentBatchStartDate = student.currentBatchStartDate
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