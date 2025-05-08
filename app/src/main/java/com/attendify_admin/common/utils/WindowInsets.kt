package com.attendify_admin.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusBarColor(
    color: Color,
    darkIcons: Boolean = true
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(color, darkIcons) {
        systemUiController.setStatusBarColor(color, darkIcons = darkIcons)
    }
}
