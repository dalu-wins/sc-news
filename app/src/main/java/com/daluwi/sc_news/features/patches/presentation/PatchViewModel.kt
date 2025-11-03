package com.daluwi.sc_news.features.patches.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.patches.domain.error_handling.RepositoryError
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.error_handling.asUiText
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.use_case.PatchType
import com.daluwi.sc_news.features.patches.domain.use_case.PatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatchViewModel @Inject constructor(
    private val patchUseCases: PatchUseCases,
) : ViewModel() {

    companion object {
        private const val SPECTRUM_PATCH_NOTES: String =
            "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048?sort=hot&page=1"
    }

    private val _state = mutableStateOf(PatchState())
    val state: State<PatchState> = _state

    private val errorChannel = Channel<PatchEvent.Error>(
        capacity = 3, // Prevent infinitely long queues blocking the screen
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val errors = errorChannel.receiveAsFlow()

    init {
        loadLocal()
        loadRemote()
    }

    fun onEvent(event: PatchEvent) {
        when (event) {
            is PatchEvent.Refresh -> {
                loadRemote()
            }

            is PatchEvent.VisitSpectrum -> {
                event.uriHandler.openUri(SPECTRUM_PATCH_NOTES)
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
            val pinned = patchUseCases.getLocalPatches(PatchType.PINNED)
            val other = patchUseCases.getLocalPatches(PatchType.NOT_PINNED)
            setPinned(pinned)
            setOther(other)
        }
    }

    private fun loadRemote() {
        if (_state.value.isLoading) return
        viewModelScope.launch {

            setLoading(true)

            _state.value = state.value.copy(isLoading = true)
            val pinned = patchUseCases.getRemotePatches(PatchType.PINNED)
            val other = patchUseCases.getRemotePatches(PatchType.NOT_PINNED)
            setPinned(pinned)
            setOther(other)

            delay(300)
            setLoading(false)

        }
    }

    private fun setPinned(pinned: Result<List<Patch>, RepositoryError>) {
        when (pinned) {
            is Result.Success -> _state.value = state.value.copy(pinnedPatches = pinned.data)
            is Result.Error -> onEvent(PatchEvent.Error(message = pinned.error.asUiText()))
        }
    }

    private fun setOther(other: Result<List<Patch>, RepositoryError>) {
        when (other) {
            is Result.Success -> _state.value = state.value.copy(otherPatches = other.data)
            is Result.Error -> onEvent(PatchEvent.Error(message = other.error.asUiText()))
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _state.value = state.value.copy(isLoading = isLoading)
    }

}