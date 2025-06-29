package com.attendify_admin.home.feature_users.presentation.remove_from_dropout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyDropDownMenuBox
import com.attendify_admin.home.feature_users.presentation.remove_from_dropout.components.StudentCard

@Composable
fun RemoveFromDropoutScreen(
    modifier: Modifier = Modifier,
    state: RemoveFromDropoutState,
    onEvent: (RemoveFromDropoutEvent) -> Unit,
    onSelectStudents: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.widthIn(max = UiConstants.MAX_WIDTH)
        ) {
            Text(
                text = "Select dropout year",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
            )
            // Semester Dropdown
            AttendifyDropDownMenuBox(
                expanded = state.isDropoutAcademicYearDropdownExpanded,
                onDropDownVisibilityChanged = {
                    onEvent(
                        RemoveFromDropoutEvent.AcademicYearDropdownVisibilityChanged(
                            it
                        )
                    )
                },
                value = state.selectedDropoutAcademicYear?.toString() ?: "",
                enabled = true,
                options = state.academicYearOfDropoutOptions,
                onSelectItem = {
                    onEvent(RemoveFromDropoutEvent.AcademicYearChanged(it))
                },
                label = "Dropout year"
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Selected students",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            if (state.selectedStudentIds.isEmpty()) {
                AttendifyButton(
                    onClick = onSelectStudents
                ) {
                    Text("Select students")
                }
            } else {
                LazyColumn {
                    items(
                        items = state.selectedStudents,
                        key = {
                            it.id
                        }
                    ) { student ->
                        StudentCard(
                            studentName = student.studentName,
                            studentImageUrl = student.studentImageUrl,
                            onRemoveFromDropout = { onEvent(RemoveFromDropoutEvent.RemoveStudentFromDropout(student.id)) },
                            isRemoved = student.isRemoved,
                            isFailedToRemove = student.isFailedToRemove,
                            supportingText = student.supportingText,
                            isRemoving = student.isRemoving
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}