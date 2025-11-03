package com.daluwi.sc_news.features.patches.data.source.remote

data class PatchesDataWrapper(
    val cacheStatus: String,
    val timestamp: String,
    val patches: List<PatchApiObject> = emptyList()
)