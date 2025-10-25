package com.daluwi.sc_news.features.patches.data.repository

import com.daluwi.sc_news.features.patches.data.source.PatchDAO
import com.daluwi.sc_news.features.patches.data.source.toDomain
import com.daluwi.sc_news.features.patches.data.source.toEntity
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PatchRepositoryImpl(
    private val dao: PatchDAO
) : PatchRepository {
    override fun getPatches(): Flow<List<Patch>> {
        return dao.getBuilds().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getPatchByChannel(channel: Channel): Patch? {
        val entity = dao.getBuildByChannel(channel) ?: return null
        return entity.toDomain()
    }

    override suspend fun insertPatch(patch: Patch) {
        dao.insertBuild(patch.toEntity())
    }

    override suspend fun insertAll(patches: List<Patch>) {
        dao.insertAll(patches.map { it.toEntity() })
    }

    override suspend fun deletePatch(patch: Patch) {
        dao.deleteBuild(patch.toEntity())
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}