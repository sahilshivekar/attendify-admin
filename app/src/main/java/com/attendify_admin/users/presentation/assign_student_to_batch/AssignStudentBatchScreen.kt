package com.attendify_admin.users.presentation.assign_student_to_batch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AssignStudentBatchScreen(
    modifier: Modifier = Modifier,
    state: AssignStudentBatchState,
    onEvent: (AssignStudentBatchEvent) -> Unit
) {
    Text(text = "assign student batch")
}