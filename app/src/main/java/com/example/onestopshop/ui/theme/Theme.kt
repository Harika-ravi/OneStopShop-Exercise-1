package com.example.onestopshop.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary              = LuxeBlack,
    onPrimary            = LuxeWhite,
    primaryContainer     = LuxeCharcoal,
    onPrimaryContainer   = LuxeOffWhite,
    secondary            = LuxeGold,
    onSecondary          = LuxeBlack,
    secondaryContainer   = LuxeGoldLight,
    onSecondaryContainer = LuxeCharcoal,
    background           = LuxeOffWhite,
    onBackground         = LuxeBlack,
    surface              = LuxeWhite,
    onSurface            = LuxeBlack,
    surfaceVariant       = LuxeBeige,
    onSurfaceVariant     = LuxeDarkGray,
    outline              = LuxeLightGray,
    error                = LuxeRed,
    onError              = LuxeWhite
)

private val DarkColors = darkColorScheme(
    primary              = LuxeOffWhite,
    onPrimary            = LuxeBlack,
    primaryContainer     = LuxeCharcoal,
    onPrimaryContainer   = LuxeOffWhite,
    secondary            = LuxeGold,
    onSecondary          = LuxeBlack,
    secondaryContainer   = LuxeDarkGray,
    onSecondaryContainer = LuxeGoldLight,
    background           = LuxeCharcoal,
    onBackground         = LuxeOffWhite,
    surface              = LuxeDarkGray,
    onSurface            = LuxeOffWhite,
    surfaceVariant       = androidx.compose.ui.graphics.Color(0xFF2A2420),
    onSurfaceVariant     = LuxeGray,
    outline              = androidx.compose.ui.graphics.Color(0xFF3A3A3A),
    error                = LuxeRed,
    onError              = LuxeWhite
)

@Composable
fun OneStopShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography  = LuxeTypography,
        content     = content
    )
}