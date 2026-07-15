package com.daluwi.sc_news.features.settings.presentation

import androidx.compose.ui.graphics.Color

sealed class SettingsEvent {
    data class UseDynamicColors(val dynamicColors: Boolean) : SettingsEvent()
    data class ShowBuild(val build: Boolean) : SettingsEvent()
    data class SetCustomColor(val color: Color) : SettingsEvent()
}