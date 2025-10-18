package com.daluwi.sc_newshub.domain.use_case

import com.daluwi.sc_newshub.domain.models.LiveBuild
import com.daluwi.sc_newshub.domain.repository.LiveBuildRepository
import kotlinx.coroutines.flow.Flow

class GetLiveBuildsUseCase(
    private val repository: LiveBuildRepository
) {
    operator fun invoke(): Flow<List<LiveBuild>> {
        return repository.getLiveBuilds()
    }
}