package com.attendify_admin.users.presentation.assign_student_to_semester

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AssignStudentSemesterScreen(
    modifier: Modifier = Modifier,
    state: AssignStudentSemesterState,
    onEvent: (AssignStudentSemesterEvent) -> Unit
) {
    Text(text = "assign student semester")
}