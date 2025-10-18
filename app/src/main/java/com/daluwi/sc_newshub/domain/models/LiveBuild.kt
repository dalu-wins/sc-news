package com.daluwi.sc_newshub.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LiveBuild (
    @PrimaryKey val channel: Channel,
    val version: Version,
    val build: String
)
