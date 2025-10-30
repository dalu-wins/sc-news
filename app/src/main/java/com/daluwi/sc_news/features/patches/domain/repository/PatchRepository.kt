package com.daluwi.sc_news.features.patches.domain.repository

import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch

interface PatchRepository {

    suspend fun getPatches(): List<Patch>

    suspend fun getLocalPatches(): List<Patch>

    suspend fun getRemotePatches(): List<Patch>

    suspend fun getPatchByChannel(channel: Channel): Patch?

    suspend fun insertPatch(patch: Patch)

    suspend fun insertAll(patches: List<Patch>)

    suspend fun deletePatch(patch: Patch)

    suspend fun deleteAll()

}