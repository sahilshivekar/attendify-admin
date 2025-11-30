package com.presencify_admin.home.feature_academics.presentation.manage_batch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.presencify_admin.ui.theme.PresencifyAdminTheme

@Composable
fun BatchRoot(
    viewModel: BatchViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BatchScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun BatchScreen(
    state: BatchState,
    onAction: (BatchAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    PresencifyAdminTheme {
        BatchScreen(
            state = BatchState(),
            onAction = {}
        )
    }
}