package com.daluwi.sc_news.features.patches.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.daluwi.sc_news.core.connectivity.NetworkChecker
import com.daluwi.sc_news.features.patches.data.source.local.all.PatchDAO
import com.daluwi.sc_news.features.patches.data.source.local.all.PatchDatabase
import com.daluwi.sc_news.features.patches.data.source.local.all.toDomain
import com.daluwi.sc_news.features.patches.data.source.local.all.toEntity
import com.daluwi.sc_news.features.patches.data.source.remote.all.PatchApi
import com.daluwi.sc_news.features.patches.data.source.remote.all.toDomain
import com.daluwi.sc_news.features.patches.domain.error_handling.RepositoryError
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository

class PatchRepositoryImpl(
    private val db: PatchDatabase,
    private val api: PatchApi,
    private val networkChecker: NetworkChecker
) : PatchRepository {

    private val dao: PatchDAO = db.patchDAO

    override suspend fun getPatches(): Result<List<Patch>, RepositoryError> {
        return if (networkChecker.isAvailable()) {
            getRemotePatches()
        } else {
            getLocalPatches()
        }
    }

    override suspend fun getLocalPatches(): Result<List<Patch>, RepositoryError> {
        return try {
            Log.i("LOCAL", "loaded successfully")
            Result.Success(dao.getPatches().map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("LOCAL", "Error loading local patches", e)
            Result.Error(RepositoryError.LOCAL_FAILED)
        }
    }

    override suspend fun getRemotePatches(): Result<List<Patch>, RepositoryError> {
        if (!networkChecker.isAvailable()) return Result.Error(RepositoryError.NO_INTERNET)
        return try {
            val patches = api.getPatches().map { it.toDomain() }
            db.withTransaction {
                deleteAll()
                insertAll(patches)
            }
            Log.i("REMOTE", "loaded successfully")
            Result.Success(patches)
        } catch (e: Exception) {
            Log.e("REMOTE", "Error loading remote patches", e)
            Result.Error(RepositoryError.REMOTE_FAILED)
        }
    }

    override suspend fun getPatchByChannel(channel: Channel): Result<Patch, RepositoryError> {
        val entity =
            dao.getPatchByChannel(channel) ?: return Result.Error(RepositoryError.LOCAL_FAILED)
        return Result.Success(entity.toDomain())
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