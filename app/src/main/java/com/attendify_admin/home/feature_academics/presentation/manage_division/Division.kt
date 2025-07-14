package com.attendify_admin.home.feature_academics.presentation.manage_division

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.attendify_admin.ui.theme.AttendifyAdminTheme

@Composable
fun DivisionRoot(
    viewModel: DivisionViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DivisionScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun DivisionScreen(
    state: DivisionState,
    onAction: (DivisionAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    AttendifyAdminTheme {
        DivisionScreen(
            state = DivisionState(),
            onAction = {}
        )
    }
}