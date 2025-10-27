package com.daluwi.sc_news.features.patches.data.source.local

import androidx.room.TypeConverter
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Version
import com.daluwi.sc_news.features.patches.domain.models.Wave

class Converters {

    // --- Channel ---
    @TypeConverter
    fun fromChannel(channel: Channel): String = when (channel) {
        is Channel.Live -> "Live"
        is Channel.EPTU -> "EPTU"
        is Channel.Hotfix -> "Hotfix"
        is Channel.Preview -> "Preview"
        is Channel.PTU -> "PTU:${channel.wave.name}"
    }

    @TypeConverter
    fun toChannel(value: String): Channel {
        return when {
            value.startsWith("PTU:") -> {
                val waveName = value.substringAfter("PTU:")
                val wave = runCatching { Wave.valueOf(waveName) }.getOrDefault(Wave.One)
                Channel.PTU(wave)
            }

            value == "Live" -> Channel.Live
            value == "EPTU" -> Channel.EPTU
            value == "Hotfix" -> Channel.Hotfix
            value == "Preview" -> Channel.Preview
            else -> throw IllegalArgumentException("Unknown channel value: $value")
        }
    }

    // --- Version ---
    @TypeConverter
    fun fromVersion(version: Version): String =
        "${version.mainVersion}.${version.subVersion}.${version.patch}"

    @TypeConverter
    fun toVersion(value: String): Version {
        val parts = value.split(".")
        return Version(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
    }
    
}
