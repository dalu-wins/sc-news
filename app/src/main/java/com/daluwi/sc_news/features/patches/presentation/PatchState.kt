package com.daluwi.sc_news.features.patches.presentation

import com.daluwi.sc_news.features.patches.domain.models.Patch

data class PatchState(
    var isLoading: Boolean = true,
    val patches: List<Patch> = emptyList()
)
