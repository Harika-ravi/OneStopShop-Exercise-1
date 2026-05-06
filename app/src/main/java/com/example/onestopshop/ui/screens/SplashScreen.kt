package com.example.onestopshop.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onestopshop.ui.theme.LuxeGold
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onShopNow: () -> Unit) {

    var logoVisible  by remember { mutableStateOf(false) }
    var tagVisible   by remember { mutableStateOf(false) }
    var pillsVisible by remember { mutableStateOf(false) }
    var ctaVisible   by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(150); logoVisible  = true
        delay(500); tagVisible   = true
        delay(350); pillsVisible = true
        delay(350); ctaVisible   = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFF0A0A0A),
                        0.6f to Color(0xFF141210),
                        1.0f to Color(0xFF0A0A0A)
                    )
                )
            )
    ) {
        // Gold glow top right
        Box(
            modifier = Modifier
                .size(420.dp)
                .align(Alignment.TopEnd)
                .offset(x = 100.dp, y = (-60).dp)
                .background(
                    Brush.radialGradient(listOf(Color(0x18C9A96E), Color.Transparent))
                )
        )
        // Gold glow bottom left
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-80).dp, y = 80.dp)
                .background(
                    Brush.radialGradient(listOf(Color(0x10C9A96E), Color.Transparent))
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 36.dp),
            verticalArrangement   = Arrangement.Center,
            horizontalAlignment   = Alignment.Start
        ) {
            // Collection label
            AnimatedVisibility(
                visible = tagVisible,
                enter   = fadeIn(tween(700)) + slideInVertically(tween(700)) { 24 }
            ) {
                Text(
                    text          = "NEW COLLECTION  ·  2024",
                    color         = LuxeGold,
                    fontSize      = 10.sp,
                    fontWeight    = FontWeight.Medium,
                    letterSpacing = 3.sp,
                    fontFamily    = FontFamily.SansSerif
                )
            }

            Spacer(Modifier.height(20.dp))

            // Brand name
            AnimatedVisibility(
                visible = logoVisible,
                enter   = fadeIn(tween(900)) + slideInVertically(tween(900)) { 60 }
            ) {
                Column {
                    Text(
                        text          = "ONE STOP",
                        color         = Color.White,
                        fontSize      = 64.sp,
                        fontWeight    = FontWeight.Thin,
                        letterSpacing = 10.sp,
                        fontFamily    = FontFamily.Serif,
                        lineHeight    = 68.sp
                    )
                    Text(
                        text          = "SHOP",
                        color         = Color.White,
                        fontSize      = 64.sp,
                        fontWeight    = FontWeight.ExtraLight,
                        letterSpacing = 16.sp,
                        fontFamily    = FontFamily.Serif,
                        lineHeight    = 70.sp
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            // Gold divider
            AnimatedVisibility(
                visible = tagVisible,
                enter   = fadeIn(tween(600))
            ) {
                HorizontalDivider(
                    modifier  = Modifier.width(56.dp),
                    color     = LuxeGold,
                    thickness = 1.dp
                )
            }

            Spacer(Modifier.height(28.dp))

            // Tagline
            AnimatedVisibility(
                visible = tagVisible,
                enter   = fadeIn(tween(900)) + slideInVertically(tween(900)) { 24 }
            ) {
                Text(
                    text          = "Refined style for the\ndiscerning individual.",
                    color         = Color(0xFFB0A898),
                    fontSize      = 18.sp,
                    fontWeight    = FontWeight.Light,
                    letterSpacing = 0.3.sp,
                    fontFamily    = FontFamily.Serif,
                    lineHeight    = 28.sp
                )
            }

            Spacer(Modifier.height(44.dp))

            // Category pills
            AnimatedVisibility(
                visible = pillsVisible,
                enter   = fadeIn(tween(600)) + slideInVertically(tween(600)) { 20 }
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("WOMEN", "MEN", "NEW IN").forEach { label ->
                        Box(
                            modifier = Modifier
                                .border(0.5.dp, Color(0xFF4A4A4A), RoundedCornerShape(0.dp))
                                .padding(horizontal = 14.dp, vertical = 7.dp)
                        ) {
                            Text(
                                text          = label,
                                color         = Color(0xFF999999),
                                fontSize      = 9.sp,
                                fontWeight    = FontWeight.Medium,
                                letterSpacing = 2.5.sp
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(64.dp))

            // Buttons
            AnimatedVisibility(
                visible = ctaVisible,
                enter   = fadeIn(tween(700)) + slideInVertically(tween(700)) { 28 }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Button(
                        onClick   = onShopNow,
                        modifier  = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape     = RoundedCornerShape(0.dp),
                        colors    = ButtonDefaults.buttonColors(
                            containerColor = LuxeGold,
                            contentColor   = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        Text(
                            text          = "EXPLORE COLLECTION",
                            fontSize      = 11.sp,
                            fontWeight    = FontWeight.Medium,
                            letterSpacing = 3.sp
                        )
                    }

                    OutlinedButton(
                        onClick  = onShopNow,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape    = RoundedCornerShape(0.dp),
                        border   = androidx.compose.foundation.BorderStroke(
                            0.5.dp, Color(0xFF606060)
                        ),
                        colors   = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text          = "VIEW ALL ITEMS",
                            fontSize      = 11.sp,
                            fontWeight    = FontWeight.Light,
                            letterSpacing = 3.sp
                        )
                    }
                }
            }
        }

        // Bottom credit
        Text(
            text          = "CS2430  ·  LAKEHEAD UNIVERSITY",
            modifier      = Modifier
                .align(Alignment.BottomCenter)
                .systemBarsPadding()
                .padding(bottom = 20.dp)
                .alpha(0.25f),
            color         = Color.White,
            fontSize      = 8.sp,
            letterSpacing = 2.sp
        )
    }
}