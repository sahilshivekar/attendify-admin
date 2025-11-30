package com.presencify_admin.home.feature_academics.presentation.manage_course

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.presencify_admin.ui.theme.PresencifyAdminTheme

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
    PresencifyAdminTheme {
        ManageCourseScreen(
            state = ManageCourseState(),
            onAction = {}
        )
    }
}