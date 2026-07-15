package com.daluwi.sc_news.features.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.settings.domain.use_case.SettingsUseCases
import com.materialkolor.ktx.toHex
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
        loadThemeColor()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.UseDynamicColors -> {
                updateDynamicColorSetting(event.dynamicColors)
            }

            is SettingsEvent.ShowBuild -> {
                updateBuildSetting(event.build)
            }

            is SettingsEvent.SetCustomColor -> {
                updateThemeColor(event.color)
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

    fun updateThemeColor(color: Color) {
        viewModelScope.launch {
            useCases.setThemeColorUseCase(color.toHex())
            _state.value = state.value.copy(color = color)
        }
    }

    fun loadThemeColor() {
        viewModelScope.launch {
            useCases.getThemeColorUseCase().collect { colorHex ->
                _state.value = _state.value.copy(color = Color(colorHex.toColorInt()))
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