package com.daluwi.sc_news.features.patches.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.daluwi.sc_news.features.patches.domain.models.Patch

@Database(
    entities = [Patch::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BuildDatabase : RoomDatabase() {
    abstract val buildDAO: BuildDAO

    companion object {
        const val DATABASE_NAME = "builds_db"
    }
}