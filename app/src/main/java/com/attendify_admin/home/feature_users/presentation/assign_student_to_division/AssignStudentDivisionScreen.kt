package com.attendify_admin.home.feature_users.presentation.assign_student_to_division

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AssignStudentDivisionScreen(
    modifier: Modifier = Modifier,
    state: AssignStudentDivisionState,
    onEvent: (AssignStudentDivisionEvent) -> Unit
) {
    Text(text = "assign student division")
}