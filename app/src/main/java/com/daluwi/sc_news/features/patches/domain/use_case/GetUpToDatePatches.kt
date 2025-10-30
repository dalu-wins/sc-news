package com.daluwi.sc_news.features.patches.domain.use_case

import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository

class GetUpToDatePatches(
    private val repository: PatchRepository
) {
    suspend operator fun invoke(): List<Patch> {
        return repository.getPatches()
    }
}