package com.daluwi.sc_newshub.features.builds.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daluwi.sc_newshub.features.builds.domain.models.Build
import com.daluwi.sc_newshub.features.builds.domain.models.Channel
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildDAO {
    @Query("SELECT * FROM Build")
    fun getBuilds(): Flow<List<Build>>

    @Query("SELECT * FROM Build WHERE channel = :channel")
    suspend fun getBuildByChannel(channel: Channel): Build?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuild(build: Build)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(builds: List<Build>)

    @Delete
    suspend fun deleteBuild(build: Build)

    @Query("DELETE FROM Build")
    suspend fun deleteAll()
}