package com.presencify_admin.home.feature_academics.presentation.manage_division

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.presencify_admin.ui.theme.PresencifyAdminTheme

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
    PresencifyAdminTheme {
        DivisionScreen(
            state = DivisionState(),
            onAction = {}
        )
    }
}