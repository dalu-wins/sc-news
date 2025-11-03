package com.daluwi.sc_news.features.patches.domain.use_case

import com.daluwi.sc_news.features.patches.domain.error_handling.RepositoryError
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository

class GetLocalPatches(
    private val repository: PatchRepository
) {
    suspend operator fun invoke(type: PatchType = PatchType.ALL): Result<List<Patch>, RepositoryError> {
        val result = repository.getLocalPatches()
        return when (result) {
            is Result.Error -> result
            is Result.Success -> when (type) {
                PatchType.PINNED -> result.copy(result.data.filter { it.pinned })
                PatchType.NOT_PINNED -> result.copy(result.data.filter { !it.pinned })
                PatchType.ALL -> result
            }
        }
    }
}