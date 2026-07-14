package com.daluwi.sc_news.features.patches.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daluwi.sc_news.features.patches.domain.models.Channel

@Dao
interface PatchDAO {
    @Query("SELECT * FROM PatchEntity")
    suspend fun getPatches(): List<PatchEntity>

    @Query("SELECT * FROM PatchEntity WHERE channel = :channel")
    suspend fun getPatchByChannel(channel: Channel): PatchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatch(patch: PatchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(patches: List<PatchEntity>)

    @Delete
    suspend fun deletePatch(patch: PatchEntity)

    @Query("DELETE FROM PatchEntity")
    suspend fun deleteAll()
}