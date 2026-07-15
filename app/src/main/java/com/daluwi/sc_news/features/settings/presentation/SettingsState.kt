package com.daluwi.sc_news.features.settings.presentation

import androidx.compose.ui.graphics.Color

data class SettingsState(
    val dynamicColors: Boolean = true,
    val build: Boolean = false,
    val color: Color = Color.Blue
)