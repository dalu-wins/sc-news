package com.daluwi.sc_newshub.data.source

import androidx.room.TypeConverter
import com.daluwi.sc_newshub.domain.models.Channel
import com.daluwi.sc_newshub.domain.models.Version

class Converters {

    @TypeConverter
    fun fromChannel(channel: Channel): String = channel.name

    @TypeConverter
    fun toChannel(value: String): Channel = Channel.valueOf(value)

    @TypeConverter
    fun fromVersion(version: Version): String =
        "${version.mainVersion}.${version.subVersion}.${version.patch}"

    @TypeConverter
    fun toVersion(value: String): Version {
        val parts = value.split(".")
        return Version(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
    }
}