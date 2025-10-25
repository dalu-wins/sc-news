package com.daluwi.sc_news.features.patches.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daluwi.sc_news.features.patches.domain.models.Channel
import kotlinx.coroutines.flow.Flow

@Dao
interface PatchDAO {
    @Query("SELECT * FROM PatchEntity")
    fun getBuilds(): Flow<List<PatchEntity>>

    @Query("SELECT * FROM PatchEntity WHERE channel = :channel")
    suspend fun getBuildByChannel(channel: Channel): PatchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuild(patch: PatchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(patches: List<PatchEntity>)

    @Delete
    suspend fun deleteBuild(patch: PatchEntity)

    @Query("DELETE FROM PatchEntity")
    suspend fun deleteAll()
}