package com.daluwi.sc_news.features.patches.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.patches.domain.error_handling.RepositoryError
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.error_handling.asUiText
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.use_case.PatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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

    private val eventChannel = Channel<PatchEvent.Error>()
    val events = eventChannel.receiveAsFlow()

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
                    eventChannel.send(PatchEvent.Error(event.message))
                }
            }
        }
    }

    private fun loadLocal() {
        viewModelScope.launch {
            val result = patchUseCases.getLocalPatches()
            processPatchesResult(result)
        }
    }

    private fun loadRemote() {
        if (_state.value.isLoading) return
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = patchUseCases.getRemotePatches()
            processPatchesResult(result)
        }
    }

    private fun processPatchesResult(result: Result<List<Patch>, RepositoryError>) {
        when (result) {
            is Result.Success -> _state.value = state.value.copy(patches = result.data)
            is Result.Error -> onEvent(PatchEvent.Error(result.error.asUiText()))
        }
        _state.value = state.value.copy(isLoading = false)
    }

}