package com.daluwi.sc_news.features.patches.data.source.remote.notes

import retrofit2.http.GET
import retrofit2.http.Query

interface PatchNotesApiService {

    @GET("/patch-notes/thread")
    suspend fun getPatchNotes(@Query("url_base64") urlBase64: String): PatchNotesApiResponse

}