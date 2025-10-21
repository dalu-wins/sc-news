package com.daluwi.sc_newshub.features.patches.domain.repository

import com.daluwi.sc_newshub.features.patches.domain.models.Channel
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import kotlinx.coroutines.flow.Flow

interface PatchRepository {

    fun getPatches(): Flow<List<Patch>>

    suspend fun getPatchByChannel(channel: Channel): Patch?

    suspend fun insertPatch(patch: Patch)

    suspend fun insertAll(patches: List<Patch>)

    suspend fun deletePatch(patch: Patch)

    suspend fun deleteAll()

}