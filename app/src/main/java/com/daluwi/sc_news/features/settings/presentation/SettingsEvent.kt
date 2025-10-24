package com.daluwi.sc_news.features.settings.presentation

sealed class SettingsEvent {
    data class UseDynamicColors(val dynamicColors: Boolean) : SettingsEvent()
}