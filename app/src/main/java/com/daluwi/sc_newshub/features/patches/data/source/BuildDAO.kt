package com.daluwi.sc_newshub.features.patches.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daluwi.sc_newshub.features.patches.domain.models.Channel
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildDAO {
    @Query("SELECT * FROM Patch")
    fun getBuilds(): Flow<List<Patch>>

    @Query("SELECT * FROM Patch WHERE channel = :channel")
    suspend fun getBuildByChannel(channel: Channel): Patch?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuild(patch: Patch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(patches: List<Patch>)

    @Delete
    suspend fun deleteBuild(patch: Patch)

    @Query("DELETE FROM Patch")
    suspend fun deleteAll()
}