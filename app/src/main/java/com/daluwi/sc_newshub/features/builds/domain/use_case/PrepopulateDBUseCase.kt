package com.daluwi.sc_newshub.features.builds.domain.use_case

import com.daluwi.sc_newshub.features.builds.domain.models.Build
import com.daluwi.sc_newshub.features.builds.domain.models.Channel
import com.daluwi.sc_newshub.features.builds.domain.models.Version
import com.daluwi.sc_newshub.features.builds.domain.repository.BuildRepository

/**
 * TODO For testing purposes only. Delete after implementing proper loading of live builds from official sources
 */
class PrepopulateDBUseCase(
    private val repository: BuildRepository
) {
    suspend operator fun invoke() {
        repository.deleteAll()
        repository.insertAll(
            listOf(
                Build(Channel.Live, Version(4, 3, 1), "986543"),
                Build(Channel.PTU, Version(4, 3, 2), "999111"),
                Build(Channel.EPTU, Version(4, 3, 2), "9991122")
            )
        )
    }
}