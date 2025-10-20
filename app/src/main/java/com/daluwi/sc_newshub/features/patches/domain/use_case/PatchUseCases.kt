package com.daluwi.sc_newshub.features.patches.domain.use_case

data class PatchUseCases(
    val getPatchesUseCase: GetPatchesUseCase,
    val prepopulateDBUseCase: PrepopulateDBUseCase,
)