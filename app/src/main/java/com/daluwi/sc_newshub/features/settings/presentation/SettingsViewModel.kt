package com.daluwi.sc_newshub.features.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    // TODO Impl: private val settingsUseCases: SettingUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SetDynamicColors -> {

            }
        }
    }

}