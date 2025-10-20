package com.daluwi.sc_newshub.features.settings.presentation

sealed class SettingsEvent {
    data class SetDynamicColors(val dynamicColors: Boolean) : SettingsEvent()
}