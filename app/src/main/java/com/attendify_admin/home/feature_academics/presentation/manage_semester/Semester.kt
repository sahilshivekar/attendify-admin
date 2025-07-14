package com.attendify_admin.home.feature_academics.presentation.manage_semester

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.attendify_admin.ui.theme.AttendifyAdminTheme

@Composable
fun SemesterRoot(
    viewModel: SemesterViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SemesterScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SemesterScreen(
    state: SemesterState,
    onAction: (SemesterAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    AttendifyAdminTheme {
        SemesterScreen(
            state = SemesterState(),
            onAction = {}
        )
    }
}