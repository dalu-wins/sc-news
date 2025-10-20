package com.daluwi.sc_newshub.features.settings.presentation

sealed class SettingsEvent {
    data class UseDynamicColors(val useDynamicColors: Boolean) : SettingsEvent()
}