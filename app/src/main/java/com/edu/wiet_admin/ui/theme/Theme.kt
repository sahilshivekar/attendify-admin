package com.edu.wiet_admin.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkThemeBlue,
    onPrimary = Color.White,
    inversePrimary = DarkThemeLightBlue,

    background = DarkThemeBackground,
    onBackground = Color.White,

    secondary = DarkThemeDarkBlue,
    onSecondary = DarkThemeLightBlue,

    surface = DarkThemeSurface,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = LightThemeBlue,
    onPrimary = Color.White,
    inversePrimary = LightThemeLightBlue,

    background = LightThemeBackground,
    onBackground = Color.Black,

    secondary = LightThemeLightBlue,
    onSecondary = LightThemeDarkBlue,

    surface = LightThemeSurface,
    onSurface = Color.Black,
)

@Composable
fun WietAdminTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}