package com.daluwi.sc_newshub.features.patches.presentation

import com.daluwi.sc_newshub.features.patches.domain.models.Patch

data class PatchState(
    val patches: List<Patch> = emptyList()
)
