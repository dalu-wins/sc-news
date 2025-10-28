package com.daluwi.sc_news.features.patches.data.source.remote

data class PatchApiResponse(
    val status: String,
    val data: PatchesDataWrapper?
)