package com.example.onestopshop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LuxeTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Serif, fontWeight = FontWeight.Thin,
        fontSize = 72.sp, lineHeight = 76.sp, letterSpacing = 12.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Serif, fontWeight = FontWeight.Light,
        fontSize = 48.sp, lineHeight = 52.sp, letterSpacing = 4.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif, fontWeight = FontWeight.Normal,
        fontSize = 32.sp, lineHeight = 40.sp, letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light,
        fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 6.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light,
        fontSize = 20.sp, lineHeight = 28.sp, letterSpacing = 8.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal,
        fontSize = 15.sp, lineHeight = 22.sp, letterSpacing = 2.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal,
        fontSize = 15.sp, lineHeight = 22.sp, letterSpacing = 0.3.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light,
        fontSize = 13.sp, lineHeight = 19.sp, letterSpacing = 0.2.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium,
        fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 2.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal,
        fontSize = 10.sp, lineHeight = 14.sp, letterSpacing = 1.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal,
        fontSize = 9.sp, lineHeight = 12.sp, letterSpacing = 1.5.sp
    )
)