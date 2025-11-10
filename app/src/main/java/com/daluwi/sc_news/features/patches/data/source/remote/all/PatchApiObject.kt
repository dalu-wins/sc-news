package com.daluwi.sc_news.features.patches.data.source.remote.all

data class PatchApiObject(
    val sourceUrl: String,
    val pinned: Boolean,
    val currentlyOnline: Boolean,
    val channel: String,
    val wave: String?,
    val version: ApiVersion,
    val build: String
)

data class ApiVersion(
    val major: Int,
    val minor: Int,
    val patch: Int
)
