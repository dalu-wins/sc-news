package com.daluwi.sc_news.features.patches.data.source.remote.all

import retrofit2.http.GET

interface PatchApiService {

    @GET("/patch-notes/all")
    suspend fun getPatches(): PatchApiResponse

}