package com.daluwi.sc_news.features.patches.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patch(
    @PrimaryKey val sourceUrl: String,
    val channel: Channel,
    val version: Version,
    val build: String,
    val pinned: Boolean = false,
)
