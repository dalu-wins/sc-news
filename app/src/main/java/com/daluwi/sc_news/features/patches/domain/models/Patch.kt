package com.daluwi.sc_news.features.patches.domain.models

data class Patch(
    val sourceUrl: String,
    val channel: Channel,
    val version: Version,
    val build: String,
    val pinned: Boolean = false,
)
