package com.attendify_admin.users.presentation.remove_student_from_semester

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RemoveStudentSemesterScreen(
    modifier: Modifier = Modifier,
    state: RemoveStudentSemesterState,
    onEvent: (RemoveStudentSemesterEvent) -> Unit
) {
    Text(text = "Remove Student Semester")
}