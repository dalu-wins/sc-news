package com.daluwi.sc_newshub.features.patches.data.repository

import com.daluwi.sc_newshub.features.patches.data.source.BuildDAO
import com.daluwi.sc_newshub.features.patches.domain.models.Channel
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.domain.repository.PatchRepository
import kotlinx.coroutines.flow.Flow

class PatchRepositoryImpl(
    private val dao: BuildDAO
) : PatchRepository {
    override fun getPatches(): Flow<List<Patch>> {
        return dao.getBuilds()
    }

    override suspend fun getPatchByChannel(channel: Channel): Patch? {
        return dao.getBuildByChannel(channel)
    }

    override suspend fun insertPatch(patch: Patch) {
        dao.insertBuild(patch)
    }

    override suspend fun insertAll(patches: List<Patch>) {
        dao.insertAll(patches)
    }

    override suspend fun deletePatch(patch: Patch) {
        dao.deleteBuild(patch)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}