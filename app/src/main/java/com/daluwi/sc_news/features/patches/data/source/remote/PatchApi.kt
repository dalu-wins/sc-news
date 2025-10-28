package com.daluwi.sc_news.features.patches.data.source.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

// TODO Let users put their own URL via settings
private const val BASE_URL = "http://10.0.2.2:8000/"

class PatchApi {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PatchApiService = retrofit.create(PatchApiService::class.java)

    suspend fun getPatches(): List<PatchApiObject> {
        try {
            val response = service.getPatches()

            if (response.status == "success" && response.data != null) {
                return response.data.threads
            } else {
                throw IOException("API returned failure status: ${response.status}")
            }
        } catch (e: Exception) {
            throw IOException("Failed to fetch threads from API: ${e.message}", e)
        }
    }
}