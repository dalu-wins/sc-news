package com.daluwi.sc_news.features.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.settings.domain.use_case.SettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: SettingsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    init {
        loadDynamicColorSetting()
        loadBuildSetting()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.UseDynamicColors -> {
                updateDynamicColorSetting(event.dynamicColors)
            }

            is SettingsEvent.ShowBuild -> {
                updateBuildSetting(event.build)
            }
        }
    }

    fun updateDynamicColorSetting(dynamicColor: Boolean) {
        viewModelScope.launch {
            useCases.setDynamicColorUseCase(dynamicColor)
            _state.value = state.value.copy(dynamicColors = dynamicColor)
        }
    }

    fun loadDynamicColorSetting() {
        viewModelScope.launch {
            useCases.getDynamicColorUseCase().collect { dynamicColor ->
                _state.value = _state.value.copy(dynamicColors = dynamicColor)
            }
        }
    }

    fun updateBuildSetting(build: Boolean) {
        viewModelScope.launch {
            useCases.setBuildSettingUseCase(build)
            _state.value = state.value.copy(build = build)
        }
    }

    fun loadBuildSetting() {
        viewModelScope.launch {
            useCases.getBuildSettingUseCase().collect { build ->
                _state.value = _state.value.copy(build = build)
            }
        }
    }

}