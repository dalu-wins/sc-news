package com.daluwi.sc_newshub.features.builds.domain.use_case

import com.daluwi.sc_newshub.features.builds.domain.models.Build
import com.daluwi.sc_newshub.features.builds.domain.repository.BuildRepository
import kotlinx.coroutines.flow.Flow

class GetBuildsUseCase(
    private val repository: BuildRepository
) {
    operator fun invoke(): Flow<List<Build>> {
        return repository.getBuilds()
    }
}