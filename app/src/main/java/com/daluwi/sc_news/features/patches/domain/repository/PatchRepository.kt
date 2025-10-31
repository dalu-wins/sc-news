package com.daluwi.sc_news.features.patches.domain.repository

import com.daluwi.sc_news.core.error_handling.RepositoryError
import com.daluwi.sc_news.core.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch

interface PatchRepository {

    suspend fun getPatches(): Result<List<Patch>, RepositoryError>

    suspend fun getLocalPatches(): Result<List<Patch>, RepositoryError>

    suspend fun getRemotePatches(): Result<List<Patch>, RepositoryError>

    suspend fun getPatchByChannel(channel: Channel): Result<Patch, RepositoryError>

    suspend fun insertPatch(patch: Patch)

    suspend fun insertAll(patches: List<Patch>)

    suspend fun deletePatch(patch: Patch)

    suspend fun deleteAll()

}