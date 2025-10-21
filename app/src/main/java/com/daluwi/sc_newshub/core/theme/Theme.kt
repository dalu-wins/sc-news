package com.daluwi.sc_newshub.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.materialkolor.dynamicColorScheme

private val RSI_BLUE = Color(red = 10, green = 29, blue = 41)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SCNewsHubTheme(
    dynamicColor: Boolean,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val dynamicColorScheme =
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)

    val colorScheme =
        if (darkTheme) dynamicColorScheme(
            primary = RSI_BLUE,
            isDark = true
        ) else dynamicColorScheme(
            primary = RSI_BLUE,
            isDark = false
        )

    MaterialExpressiveTheme(
        colorScheme = if (dynamicColor) dynamicColorScheme else colorScheme,
        content = content
    )
}