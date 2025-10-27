package com.daluwi.sc_news.features.patches.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Version

@Entity
data class PatchEntity(
    @PrimaryKey val sourceUrl: String,
    val channel: Channel,
    val version: Version,
    val build: String,
    val pinned: Boolean = false,
)