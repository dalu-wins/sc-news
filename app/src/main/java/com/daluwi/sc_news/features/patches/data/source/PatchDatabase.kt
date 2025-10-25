package com.daluwi.sc_news.features.patches.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PatchEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PatchDatabase : RoomDatabase() {
    abstract val patchDAO: PatchDAO

    companion object {
        const val DATABASE_NAME = "builds_db"
    }
}