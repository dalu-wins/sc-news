package com.daluwi.sc_news.features.patches.data.source.remote

import retrofit2.http.GET

interface PatchApiService {

    @GET("/api/patches")
    suspend fun getPatches(): PatchApiResponse
}