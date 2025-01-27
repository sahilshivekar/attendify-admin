package com.edu.wiet_admin.common.presentation


import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.edu.wiet_admin.ui.theme.WietAdminTheme

/**
 * This preview annotation class is used to show component previews in both light and dark mode.
 */
@Preview(
    showBackground = true
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class ComponentPreview()

/**
 * This preview annotation class is used to show screen previews in both light and dark mode.
 */
@Preview(
    showSystemUi = true,
    device = Devices.PIXEL_7_PRO
)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_7_PRO
)
annotation class ScreenPreview()

/**
 * This function is used to apply the [WietAdminTheme] and a [Surface] behind the composable.
 */
@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    WietAdminTheme() {
        Surface {
            content()
        }
    }
}