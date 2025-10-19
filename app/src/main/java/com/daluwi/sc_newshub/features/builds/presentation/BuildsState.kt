package com.daluwi.sc_newshub.features.builds.presentation

import com.daluwi.sc_newshub.features.builds.domain.models.Build

data class BuildsState(
    val builds: List<Build> = emptyList()
)
