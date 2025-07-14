package com.attendify_admin.home.feature_academics.presentation.manage_course

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.attendify_admin.ui.theme.AttendifyAdminTheme

@Composable
fun ManageCourseRoot(
    viewModel: ManageCourseViewModel,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ManageCourseScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ManageCourseScreen(
    state: ManageCourseState,
    onAction: (ManageCourseAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    AttendifyAdminTheme {
        ManageCourseScreen(
            state = ManageCourseState(),
            onAction = {}
        )
    }
}