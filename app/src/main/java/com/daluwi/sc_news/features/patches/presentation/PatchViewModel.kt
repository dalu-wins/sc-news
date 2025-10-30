package com.daluwi.sc_news.features.patches.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_news.features.patches.domain.use_case.PatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var getPatchesJob: Job? = null
    private var getRefreshJob: Job? = null

    init {
        initPatchList()
        getUpToDatePatches()
    }

    fun onEvent(event: PatchEvent) {
        when (event) {
            is PatchEvent.Refresh -> {
                refresh()
            }

            is PatchEvent.VisitSpectrum -> {
                event.uriHandler.openUri(SPECTRUM_PATCH_NOTES)
            }

            is PatchEvent.VisitThread -> {
                event.uriHandler.openUri(event.threadUrl)
            }
        }
    }

    private fun initPatchList() {
        viewModelScope.launch {
            val patches = patchUseCases.getLocalPatches()
            _state.value = state.value.copy(patches = patches)
        }
    }

    private fun getUpToDatePatches() {
        getPatchesJob?.cancel()
        getPatchesJob = viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val patches = patchUseCases.getUpToDatePatches()
            _state.value = state.value.copy(isLoading = false, patches = patches)
        }
    }

    private fun refresh() {
        getRefreshJob?.cancel()
        getRefreshJob = viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val patches = patchUseCases.refreshPatches()
            _state.value = state.value.copy(isLoading = false, patches = patches)
        }
    }

}