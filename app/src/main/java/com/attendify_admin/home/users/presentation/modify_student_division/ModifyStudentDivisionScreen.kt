package com.attendify_admin.home.users.presentation.modify_student_division

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ModifyStudentDivisionScreen(
    modifier: Modifier = Modifier,
    state: ModifyStudentDivisionState,
    onEvent: (ModifyStudentDivisionEvent) -> Unit
) {
    Text(text = "Modify student division")
}