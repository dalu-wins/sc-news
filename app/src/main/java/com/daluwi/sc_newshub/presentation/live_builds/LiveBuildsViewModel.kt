package com.daluwi.sc_newshub.presentation.live_builds

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daluwi.sc_newshub.data.source.LiveBuildDatabase
import com.daluwi.sc_newshub.domain.models.Channel
import com.daluwi.sc_newshub.domain.models.LiveBuild
import com.daluwi.sc_newshub.domain.models.Version
import com.daluwi.sc_newshub.domain.use_case.GetLiveBuildsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveBuildsViewModel @Inject constructor(
    private val getLiveBuildsUseCase: GetLiveBuildsUseCase,
    private val db: LiveBuildDatabase //TODO: delete later, only for prepopulating for testing purposes
) : ViewModel() {

    private val _state = mutableStateOf(LiveBuildsState())
    val state: State<LiveBuildsState> = _state

    private var getLiveBuildsJob: Job? = null

    init {
        preloadData() //TODO: delete later, only for prepopulating for testing purposes
        getLiveBuilds()
    }

    private fun getLiveBuilds() {
        getLiveBuildsJob?.cancel()
        getLiveBuildsJob = getLiveBuildsUseCase().onEach { liveBuilds ->
                _state.value = state.value.copy(liveBuilds = liveBuilds)
            }
            .launchIn(viewModelScope)
    }

    //TODO: delete later, only for prepopulating for testing purposes
    private fun preloadData() {
        viewModelScope.launch {
            db.liveBuildDAO.insertAll(
                listOf(
                    LiveBuild(Channel.Live, Version(3, 25, 1), "986543"),
                    LiveBuild(Channel.PTU, Version(3, 26, 0), "999111"),
                    LiveBuild(Channel.EPTU, Version(3, 26, 1), "999222"),
                    LiveBuild(Channel.Hotfix, Version(3, 25, 1), "986550"),
                    LiveBuild(Channel.Preview, Version(3, 27, 0), "100002")
                )
            )
        }
    }

}