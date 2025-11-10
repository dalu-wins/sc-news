package com.daluwi.sc_news.features.patches.domain.repository

import com.daluwi.sc_news.features.patches.domain.error_handling.RepositoryError
import com.daluwi.sc_news.features.patches.domain.error_handling.Result

interface PatchNotesRepository {

    suspend fun getPatchNotes(urlBase64: String): Result<Map<String, String>, RepositoryError>

}