package com.attendify_admin.users.presentation.modify_student_batch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ModifyStudentBatchScreen(
    modifier: Modifier = Modifier,
    state: ModifyStudentBatchState,
    onEvent: (ModifyStudentBatchEvent) -> Unit
) {
    Text(text = "Modify student batch")
}