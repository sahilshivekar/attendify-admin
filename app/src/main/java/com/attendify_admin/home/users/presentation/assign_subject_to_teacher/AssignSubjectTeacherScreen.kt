package com.attendify_admin.home.users.presentation.assign_subject_to_teacher

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AssignSubjectTeacherScreen(
    modifier: Modifier = Modifier,
    state: AssignSubjectTeacherState,
    onEvent: (AssignSubjectTeacherEvent) -> Unit
) {
    Text(text = "assign subject teacher")
}