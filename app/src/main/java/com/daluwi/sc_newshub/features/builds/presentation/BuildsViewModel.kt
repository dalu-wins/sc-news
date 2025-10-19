package com.daluwi.sc_newshub.features.builds.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_newshub.features.builds.domain.use_case.BuildUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildsViewModel @Inject constructor(
    private val buildUseCases: BuildUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(BuildsState())
    val state: State<BuildsState> = _state

    private var getLiveBuildsJob: Job? = null

    init {
        prepopulateDB()
        getLiveBuilds()
    }

    private fun getLiveBuilds() {
        getLiveBuildsJob?.cancel()
        getLiveBuildsJob = buildUseCases.getBuildsUseCase().onEach { builds ->
            _state.value = state.value.copy(builds = builds)
        }.launchIn(viewModelScope)
    }

    private fun prepopulateDB() {
        viewModelScope.launch {
            buildUseCases.prepopulateDBUseCase()
        }
    }

}