package com.daluwi.sc_news.features.patches.data.source.remote.all

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

// TODO Let users put their own URL via settings
private const val BASE_URL = "https://sc-news.api.dalu-wins.de/"

class PatchApi {

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PatchApiService = retrofit.create(PatchApiService::class.java)

    suspend fun getPatches(): List<PatchApiObject> {
        try {
            val response = service.getPatches()

            if (response.status == "success" && response.data != null) {
                return response.data.patches
            } else {
                throw IOException("API returned failure status: ${response.status}")
            }
        } catch (e: Exception) {
            throw IOException("Failed to fetch threads from API: ${e.message}", e)
        }
    }
}