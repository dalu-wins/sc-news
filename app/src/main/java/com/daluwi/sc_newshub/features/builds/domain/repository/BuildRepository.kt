package com.daluwi.sc_newshub.features.builds.domain.repository

import com.daluwi.sc_newshub.features.builds.domain.models.Build
import com.daluwi.sc_newshub.features.builds.domain.models.Channel
import kotlinx.coroutines.flow.Flow

interface BuildRepository {

    fun getBuilds(): Flow<List<Build>>

    suspend fun getBuildByChannel(channel: Channel): Build?

    suspend fun insertBuild(build: Build)

    suspend fun insertAll(builds: List<Build>)

    suspend fun deleteBuild(build: Build)

    suspend fun deleteAll()

}