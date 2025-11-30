package com.presencify_admin.common.presentation


import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.presencify_admin.ui.theme.PresencifyAdminTheme

/**
 * This preview annotation class is used to show component previews in light mode.
 */
@Preview(
    showBackground = true
)
annotation class ComponentPreview()


/**
 * This preview annotation class is used to show component previews in dark mode.
 */
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class ComponentPreviewDarkMode()


/**
 * This preview annotation class is used to show screen previews in light mode.
 */
@Preview(
    showSystemUi = true
)
annotation class ScreenPreview()


/**
 * This preview annotation class is used to show screen previews in dark mode.
 */
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class ScreenPreviewDarkMode()


/**
 * This preview annotation class is used to show multiple screen size previews in light mode.
 */
@Preview
@PreviewScreenSizes
annotation class AllScreenPreview()


/**
 * This preview annotation class is used to show multiple screen size previews in dark mode.
 */
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@PreviewScreenSizes
annotation class AllScreenPreviewDarkMode()


/**
 * This function is used to apply the [PresencifyAdminTheme] and a [Surface] behind the composable.
 */
@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    PresencifyAdminTheme {
        Surface {
            content()
        }
    }
}