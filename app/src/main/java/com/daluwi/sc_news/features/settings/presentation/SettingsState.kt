package com.daluwi.sc_news.features.settings.presentation

import androidx.compose.ui.graphics.Color
import com.daluwi.sc_news.core.theme.BLUE

data class SettingsState(
    val dynamicColors: Boolean = true,
    val build: Boolean = false,
    val color: Color = BLUE
)