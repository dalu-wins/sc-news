package com.daluwi.sc_newshub.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.daluwi.sc_newshub.domain.models.LiveBuild

@Database(
    entities = [LiveBuild::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class LiveBuildDatabase : RoomDatabase() {
    abstract val liveBuildDAO: LiveBuildDAO

    companion object {
        const val DATABASE_NAME = "live-builds_db"
    }
}