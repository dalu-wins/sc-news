package com.daluwi.sc_news.features.patches.domain.use_case

import com.daluwi.sc_news.features.patches.domain.error_handling.RepositoryError
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository

class GetRemotePatches(
    private val repository: PatchRepository
) {
    suspend operator fun invoke(type: PatchType): Result<List<Patch>, RepositoryError> {
        val result = repository.getRemotePatches()
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