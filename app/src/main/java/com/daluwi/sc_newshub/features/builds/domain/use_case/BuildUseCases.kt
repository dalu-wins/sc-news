package com.daluwi.sc_newshub.features.builds.domain.use_case

data class BuildUseCases(
    val getBuildsUseCase: GetBuildsUseCase,
    val prepopulateDBUseCase: PrepopulateDBUseCase,
)