package com.daluwi.sc_newshub.features.builds.data.repository

import com.daluwi.sc_newshub.features.builds.data.source.BuildDAO
import com.daluwi.sc_newshub.features.builds.domain.models.Build
import com.daluwi.sc_newshub.features.builds.domain.models.Channel
import com.daluwi.sc_newshub.features.builds.domain.repository.BuildRepository
import kotlinx.coroutines.flow.Flow

class BuildRepositoryImpl(
    private val dao: BuildDAO
) : BuildRepository {
    override fun getBuilds(): Flow<List<Build>> {
        return dao.getBuilds()
    }

    override suspend fun getBuildByChannel(channel: Channel): Build? {
        return dao.getBuildByChannel(channel)
    }

    override suspend fun insertBuild(build: Build) {
        dao.insertBuild(build)
    }

    override suspend fun insertAll(builds: List<Build>) {
        dao.insertAll(builds)
    }

    override suspend fun deleteBuild(build: Build) {
        dao.deleteBuild(build)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}