package com.daluwi.sc_news.features.patches.data.source.remote

import retrofit2.http.GET

interface PatchApiService {

    @GET("/")
    suspend fun getPatches(): PatchApiResponse
}