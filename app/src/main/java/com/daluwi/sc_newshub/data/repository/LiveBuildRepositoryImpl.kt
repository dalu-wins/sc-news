package com.daluwi.sc_newshub.data.repository

import com.daluwi.sc_newshub.data.source.LiveBuildDAO
import com.daluwi.sc_newshub.domain.models.Channel
import com.daluwi.sc_newshub.domain.models.LiveBuild
import com.daluwi.sc_newshub.domain.repository.LiveBuildRepository
import kotlinx.coroutines.flow.Flow

class LiveBuildRepositoryImpl(
    private val dao: LiveBuildDAO
) : LiveBuildRepository {
    override fun getLiveBuilds(): Flow<List<LiveBuild>> {
        return dao.getLiveBuilds()
    }

    override suspend fun getLiveBuildByChannel(channel: Channel): LiveBuild? {
        return dao.getLiveBuildByChannel(channel)
    }

    override suspend fun insertLiveBuild(liveBuild: LiveBuild) {
        dao.insertLiveBuild(liveBuild)
    }

    override suspend fun deleteLiveBuild(liveBuild: LiveBuild) {
        dao.deleteLiveBuild(liveBuild)
    }
}