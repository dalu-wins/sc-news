package com.daluwi.sc_newshub.domain.repository

import com.daluwi.sc_newshub.domain.models.Channel
import com.daluwi.sc_newshub.domain.models.LiveBuild
import kotlinx.coroutines.flow.Flow

interface LiveBuildRepository {

    fun getLiveBuilds(): Flow<List<LiveBuild>>

    suspend fun getLiveBuildByChannel(channel: Channel): LiveBuild?

    suspend fun insertLiveBuild(liveBuild: LiveBuild)

    suspend fun deleteLiveBuild(liveBuild: LiveBuild)

}