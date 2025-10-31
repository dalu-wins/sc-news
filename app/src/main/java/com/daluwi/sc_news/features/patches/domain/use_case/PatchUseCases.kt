package com.daluwi.sc_news.features.patches.domain.use_case

data class PatchUseCases(
    val getRemotePatches: GetRemotePatches,
    val getLocalPatches: GetLocalPatches
)