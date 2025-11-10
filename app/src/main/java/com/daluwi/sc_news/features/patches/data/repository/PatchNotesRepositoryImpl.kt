package com.daluwi.sc_news.features.patches.data.repository

import android.util.Log
import com.daluwi.sc_news.features.patches.data.source.remote.notes.PatchNotesApi
import com.daluwi.sc_news.features.patches.domain.error_handling.RepositoryError
import com.daluwi.sc_news.features.patches.domain.error_handling.Result
import com.daluwi.sc_news.features.patches.domain.repository.PatchNotesRepository

class PatchNotesRepositoryImpl(
    private val api: PatchNotesApi,
) : PatchNotesRepository {
    override suspend fun getPatchNotes(urlBase64: String): Result<Map<String, String>, RepositoryError> {
        return try {
            val notes = api.getPatchNotes(urlBase64)
            Log.i("REMOTE NOTES", "loaded successfully")
            Result.Success(notes)
        } catch (e: Exception) {
            Log.e("REMOTE", "Error loading remote patches", e)
            Result.Error(RepositoryError.REMOTE_FAILED)
        }
    }
}