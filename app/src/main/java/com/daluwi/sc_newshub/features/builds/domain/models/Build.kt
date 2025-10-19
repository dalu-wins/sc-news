package com.daluwi.sc_newshub.features.builds.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Build (
    @PrimaryKey val channel: Channel,
    val version: Version,
    val build: String,
)
