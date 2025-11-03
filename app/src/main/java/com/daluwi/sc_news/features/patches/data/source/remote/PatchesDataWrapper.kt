package com.daluwi.sc_news.features.patches.data.source.remote

data class PatchesDataWrapper(
    val timestamp: String,
    val status: String,
    val threads: List<PatchApiObject>
)