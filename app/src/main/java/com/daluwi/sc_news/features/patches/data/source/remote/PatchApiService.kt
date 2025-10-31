package com.daluwi.sc_news.features.patches.data.source.remote

import retrofit2.http.GET

interface PatchApiService {

    @GET("?max_patches=10")
    suspend fun getPatches(): PatchApiResponse
}