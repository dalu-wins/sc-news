package com.daluwi.sc_news.app

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: SettingsRepository,
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        viewModelScope.launch {
            repository.getSettings().collect { settings ->
                _state.value = state.value.copy(
                    isLoadingSettings = false,
                    dynamicColors = settings.dynamicColors
                )
            }
            Log.d("UPDATED DC", state.value.dynamicColors.toString())
        }
    }

}
