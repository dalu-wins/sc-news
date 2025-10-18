package com.daluwi.sc_newshub.presentation.live_builds

import com.daluwi.sc_newshub.domain.models.LiveBuild

data class LiveBuildsState(
    val liveBuilds: List<LiveBuild> = emptyList()
)
