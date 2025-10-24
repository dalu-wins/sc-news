package com.daluwi.sc_news.features.patches.domain.use_case

import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository
import kotlinx.coroutines.flow.Flow

class GetPatchesUseCase(
    private val repository: PatchRepository
) {
    operator fun invoke(): Flow<List<Patch>> {
        return repository.getPatches()
    }
}