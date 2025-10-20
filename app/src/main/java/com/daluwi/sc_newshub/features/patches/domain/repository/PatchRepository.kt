package com.daluwi.sc_newshub.features.patches.domain.repository

import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.domain.models.Channel
import kotlinx.coroutines.flow.Flow

interface PatchRepository {

    fun getBuilds(): Flow<List<Patch>>

    suspend fun getBuildByChannel(channel: Channel): Patch?

    suspend fun insertBuild(patch: Patch)

    suspend fun insertAll(patches: List<Patch>)

    suspend fun deleteBuild(patch: Patch)

    suspend fun deleteAll()

}