package com.daluwi.sc_news.features.patches.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.error_handling.asUiText
import com.daluwi.sc_news.features.patches.domain.use_case.PatchUseCases
import com.daluwi.sc_news.features.settings.domain.use_case.SettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class PatchViewModel @Inject constructor(
    private val patchUseCases: PatchUseCases,
    private val settingsUseCases: SettingsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(PatchState())
    val state = _state.asStateFlow()

    private val errorChannel = Channel<PatchEvent.Error>(
        capacity = 3, // Prevent infinitely long queues blocking the screen
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val errors = errorChannel.receiveAsFlow()

    init {
        loadLocal()
        loadRemote()
        loadSettings()
    }

    fun onEvent(event: PatchEvent) {
        when (event) {
            is PatchEvent.Refresh -> {
                loadRemote()
            }

            is PatchEvent.VisitThread -> {
                event.uriHandler.openUri(event.threadUrl)
            }

            is PatchEvent.Error -> {
                viewModelScope.launch {
                    errorChannel.send(PatchEvent.Error(event.message))
                }
            }

            is PatchEvent.TogglePinnedBuildVisibility -> {
                _state.update { it.copy(isPinnedBuildVisible = !it.isPinnedBuildVisible) }
            }

            is PatchEvent.ToggleOtherBuildVisibility -> {
                _state.update { it.copy(isOtherBuildVisible = !it.isOtherBuildVisible) }
            }
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val buildSetting = settingsUseCases.getBuildSettingUseCase.invoke().first()
            _state.update {
                it.copy(isPinnedBuildVisible = buildSetting)
            }
        }
    }

    private fun loadLocal() {
        viewModelScope.launch {
            when (val patches = patchUseCases.getLocalPatches()) {
                is Result.Error -> {
                    errorChannel.send(PatchEvent.Error(patches.error.asUiText()))
                }

                is Result.Success -> {
                    val current = patches.data.filter { it.currentlyOnline }
                    val other = patches.data.filter { !it.currentlyOnline }
                    _state.update {
                        it.copy(
                            currentPatches = current,
                            otherPatches = other
                        )
                    }
                }
            }
        }
    }

    private fun loadRemote() {
        if (_state.value.isLoading) return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val patches = patchUseCases.getRemotePatches()
            delay(300.milliseconds)

            when (patches) {
                is Result.Error -> {
                    errorChannel.send(PatchEvent.Error(patches.error.asUiText()))
                    _state.update { it.copy(isLoading = false) }
                }

                is Result.Success -> {
                    val current = patches.data.filter { it.currentlyOnline }
                    val other = patches.data.filter { !it.currentlyOnline }
                    _state.update {
                        it.copy(
                            currentPatches = current,
                            otherPatches = other,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }


}