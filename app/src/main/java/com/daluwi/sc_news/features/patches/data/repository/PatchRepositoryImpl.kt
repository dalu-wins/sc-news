package com.daluwi.sc_news.features.patches.data.repository

import android.util.Log
import com.daluwi.sc_news.core.connectivity.NetworkChecker
import com.daluwi.sc_news.features.patches.data.source.local.PatchDAO
import com.daluwi.sc_news.features.patches.data.source.local.toDomain
import com.daluwi.sc_news.features.patches.data.source.local.toEntity
import com.daluwi.sc_news.features.patches.data.source.remote.PatchApi
import com.daluwi.sc_news.features.patches.data.source.remote.toDomain
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository

class PatchRepositoryImpl(
    private val dao: PatchDAO,
    private val api: PatchApi,
    private val networkChecker: NetworkChecker
) : PatchRepository {
    override suspend fun getPatches(): List<Patch> {
        return if (networkChecker.isAvailable()) {
            try {
                val patches = api.getPatches().map { it.toDomain() }
                dao.deleteAll()
                dao.insertAll(patches.map { it.toEntity() })
                return patches
            } catch (e: Exception) {
                // TODO Should implement a msg to show to the user
                Log.d("FAILSAFE", "Error connecting to server. Using DB as fallback.")
                Log.e("FAILSAFE", e.printStackTrace().toString())
                return dao.getPatches().map { it.toDomain() }
            }
        } else {
            dao.getPatches().map { it.toDomain() }
        }
    }

    override suspend fun getPatchByChannel(channel: Channel): Patch? {
        val entity = dao.getPatchByChannel(channel) ?: return null
        return entity.toDomain()
    }

    override suspend fun insertPatch(patch: Patch) {
        dao.insertPatch(patch.toEntity())
    }

    override suspend fun insertAll(patches: List<Patch>) {
        dao.insertAll(patches.map { it.toEntity() })
    }

    override suspend fun deletePatch(patch: Patch) {
        dao.deletePatch(patch.toEntity())
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}