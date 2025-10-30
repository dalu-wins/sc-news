package com.daluwi.sc_news.features.patches.domain.use_case

data class PatchUseCases(
    val getUpToDatePatches: GetUpToDatePatches,
    val refreshPatches: RefreshPatches,
    val getLocalPatches: GetLocalPatches
)