package com.daluwi.sc_news.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.toColorInt
import com.materialkolor.dynamicColorScheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SCNewsTheme(
    dynamicColor: Boolean,
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeColor: String,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val dynamicColorScheme =
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)

    val colorScheme =
        if (darkTheme) dynamicColorScheme(
            primary = Color(themeColor.toColorInt()),
            isDark = true
        ) else dynamicColorScheme(
            primary = Color(themeColor.toColorInt()),
            isDark = false
        )

    MaterialExpressiveTheme(
        colorScheme = if (dynamicColor) dynamicColorScheme else colorScheme,
        content = content
    )
}