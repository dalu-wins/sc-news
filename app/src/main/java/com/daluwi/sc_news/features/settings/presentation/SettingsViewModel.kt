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
        loadDynamicColor()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.UseDynamicColors -> {
                updateDynamicColor(event.dynamicColors)
            }
        }
    }

    fun updateDynamicColor(dynamicColor: Boolean) {
        viewModelScope.launch {
            useCases.setDynamicColorUseCase(dynamicColor)
            _state.value = state.value.copy(dynamicColors = dynamicColor)
        }
    }

    fun loadDynamicColor() {
        viewModelScope.launch {
            useCases.getDynamicColorUseCase().collect { dynamicColor ->
                _state.value = _state.value.copy(dynamicColors = dynamicColor)
            }
        }
    }

}