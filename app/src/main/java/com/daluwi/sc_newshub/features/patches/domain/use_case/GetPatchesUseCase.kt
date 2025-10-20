package com.daluwi.sc_newshub.features.patches.domain.use_case

import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.domain.repository.PatchRepository
import kotlinx.coroutines.flow.Flow

class GetPatchesUseCase(
    private val repository: PatchRepository
) {
    operator fun invoke(): Flow<List<Patch>> {
        return repository.getBuilds()
    }
}