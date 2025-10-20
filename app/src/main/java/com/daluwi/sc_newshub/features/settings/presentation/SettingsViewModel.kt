package com.daluwi.sc_newshub.features.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_newshub.features.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    init {
        viewModelScope.launch {
            repository.useDynamicColors.collect { enabled ->
                _state.value = state.value.copy(useDynamicColors = enabled)
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.UseDynamicColors -> {
                viewModelScope.launch {
                    repository.updateUseDynamicColors(event.useDynamicColors)
                }
            }
        }
    }

}