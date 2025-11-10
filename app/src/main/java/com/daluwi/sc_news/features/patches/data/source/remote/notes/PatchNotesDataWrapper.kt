package com.daluwi.sc_news.features.patches.data.source.remote.notes

data class PatchNotesDataWrapper(
    val cacheStatus: String,
    val timestamp: String,
    val url: String,
    val notes: Map<String, String> = emptyMap()
)