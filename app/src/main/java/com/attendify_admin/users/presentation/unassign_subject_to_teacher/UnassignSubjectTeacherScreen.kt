package com.attendify_admin.users.presentation.unassign_subject_to_teacher

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UnassignSubjectTeacherScreen(
    modifier: Modifier = Modifier,
    state: UnassignSubjectTeacherState,
    onEvent: (UnassignSubjectTeacherEvent) -> Unit
) {
    Text(text = "assign subject teacher")
}