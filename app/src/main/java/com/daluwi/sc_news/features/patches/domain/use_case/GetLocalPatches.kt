package com.daluwi.sc_news.features.patches.domain.use_case

import com.daluwi.sc_news.core.error_handling.RepositoryError
import com.daluwi.sc_news.core.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository

class GetLocalPatches(
    private val repository: PatchRepository
) {
    suspend operator fun invoke(): Result<List<Patch>, RepositoryError> {
        return repository.getLocalPatches()
    }
}