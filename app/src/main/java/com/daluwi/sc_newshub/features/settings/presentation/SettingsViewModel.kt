package com.daluwi.sc_newshub.features.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_newshub.features.settings.domain.use_case.SettingsUseCases
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
        viewModelScope.launch {
            useCases.getDynamicColorUseCase().collect { isSet ->
                _state.value = state.value.copy(useDynamicColors = isSet)
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.UseDynamicColors -> {
                viewModelScope.launch {
                    useCases.setDynamicColorUseCase(event.dynamicColors)
                    _state.value = state.value.copy(useDynamicColors = event.dynamicColors)
                }
            }
        }
    }

}