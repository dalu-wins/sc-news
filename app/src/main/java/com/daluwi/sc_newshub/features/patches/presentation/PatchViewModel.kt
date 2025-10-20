package com.daluwi.sc_newshub.features.patches.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_newshub.features.patches.domain.use_case.PatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private var getLiveBuildsJob: Job? = null

    init {
        prepopulateDB()
        getLiveBuilds()
    }

    private fun getLiveBuilds() {
        getLiveBuildsJob?.cancel()
        getLiveBuildsJob = patchUseCases.getPatchesUseCase().onEach { builds ->
            _state.value = state.value.copy(patches = builds)
        }.launchIn(viewModelScope)
    }

    private fun prepopulateDB() {
        viewModelScope.launch {
            patchUseCases.prepopulateDBUseCase()
        }
    }

    fun onEvent(event: PatchEvent) {
        when (event) {
            is PatchEvent.Refresh -> {

            }

            is PatchEvent.VisitSpectrum -> {
                event.uriHandler.openUri(SPECTRUM_PATCH_NOTES)
            }

            is PatchEvent.VisitThread -> {
                event.uriHandler.openUri(event.threadUrl)
            }
        }
    }

}