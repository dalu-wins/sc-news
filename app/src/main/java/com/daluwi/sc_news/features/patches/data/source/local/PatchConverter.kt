package com.daluwi.sc_news.features.patches.data.source.local

import androidx.room.TypeConverter
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Version
import com.daluwi.sc_news.features.patches.domain.models.Wave

class PatchConverter {

    // --- Channel ---
    @TypeConverter
    fun fromChannel(channel: Channel): String = when (channel) {
        is Channel.Live -> "Live"
        is Channel.EPTU -> "EPTU"
        is Channel.Hotfix -> "Hotfix"
        is Channel.Preview -> "Preview"
        is Channel.PTU -> "PTU:${channel.wave.name}"
        is Channel.Unknown -> "Unknown"
    }

    @TypeConverter
    fun toChannel(value: String): Channel {
        return when (value) {
            "Live" -> Channel.Live
            "EPTU" -> Channel.EPTU
            "Hotfix" -> Channel.Hotfix
            "Preview" -> Channel.Preview
            else -> {
                val wave =
                    runCatching { Wave.valueOf(value.substringAfter("PTU:")) }.getOrDefault(Wave.One)
                Channel.PTU(wave)
            }
        }
    }

    // --- Version ---
    @TypeConverter
    fun fromVersion(version: Version): String =
        "${version.major}.${version.minor}.${version.patch}"

    @TypeConverter
    fun toVersion(value: String): Version {
        val parts = value.split(".")
        return Version(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
    }

}
