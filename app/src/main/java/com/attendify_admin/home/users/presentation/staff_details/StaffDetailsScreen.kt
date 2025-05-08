package com.attendify_admin.home.users.presentation.staff_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StaffDetailsScreen(
    modifier: Modifier = Modifier,
    state: StaffDetailsState,
    onEvent: (StaffDetailsEvent) -> Unit
) {
    Text(text = "assign subject teacher")
}