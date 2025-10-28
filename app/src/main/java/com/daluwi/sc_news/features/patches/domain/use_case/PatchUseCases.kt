package com.daluwi.sc_news.features.patches.domain.use_case

data class PatchUseCases(
    val getPatchesUseCase: GetPatchesUseCase,
    val refreshUseCase: RefreshUseCase
)