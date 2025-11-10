package com.daluwi.sc_news.features.patches.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.error_handling.asUiText
import com.daluwi.sc_news.features.patches.domain.repository.PatchNotesRepository
import com.daluwi.sc_news.features.patches.domain.use_case.PatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatchViewModel @Inject constructor(
    private val patchUseCases: PatchUseCases,
    private val patchNotesRepository: PatchNotesRepository,
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
        viewModelScope.launch {
            Log.d("STR", "START")
            val str =
                patchNotesRepository.getPatchNotes("aHR0cHM6Ly9yb2JlcnRzc3BhY2VpbmR1c3RyaWVzLmNvbS9zcGVjdHJ1bS9jb21tdW5pdHkvU0MvZm9ydW0vMTkwMDQ4L3RocmVhZC9zdGFyLWNpdGl6ZW4tYWxwaGEtNC00LXB0dS1wYXRjaC1ub3Rlcy00")
            Log.d("STR", str.toString())
        }
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
        }
    }

    private fun loadLocal() {
        viewModelScope.launch {
            val patches = patchUseCases.getLocalPatches()
            val newState = when (patches) {
                is Result.Error -> {
                    errorChannel.send(PatchEvent.Error(patches.error.asUiText()))
                    state.value.copy()
                }

                is Result.Success -> {
                    val current = patches.data.filter { it.currentlyOnline }
                    val other = patches.data.filter { !it.currentlyOnline }
                    state.value.copy(
                        currentPatches = current,
                        otherPatches = other
                    )
                }
            }
            _state.value = newState
        }
    }

    private fun loadRemote() {
        if (_state.value.isLoading) return
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)

            val patches = patchUseCases.getRemotePatches()
            val newState = when (patches) {
                is Result.Error -> {
                    errorChannel.send(PatchEvent.Error(patches.error.asUiText()))
                    state.value.copy(isLoading = false)
                }

                is Result.Success -> {
                    val current = patches.data.filter { it.currentlyOnline }
                    val other = patches.data.filter { !it.currentlyOnline }
                    state.value.copy(
                        currentPatches = current,
                        otherPatches = other,
                        isLoading = false
                    )
                }
            }

            delay(300)

            _state.value = newState
        }
    }


}