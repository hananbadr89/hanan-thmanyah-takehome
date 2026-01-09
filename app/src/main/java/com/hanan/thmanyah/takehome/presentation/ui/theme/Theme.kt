package com.hanan.thmanyah.takehome.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryOrange,
    onPrimary = Color.White,

    background = BackgroundDark,
    onBackground = TextPrimary,

    surface = SurfaceDark,
    onSurface = TextPrimary,

    outline = Outline,
)

@Composable
fun ThmanyahTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}