package com.daluwi.sc_newshub.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daluwi.sc_newshub.domain.models.Channel
import com.daluwi.sc_newshub.domain.models.LiveBuild
import kotlinx.coroutines.flow.Flow

@Dao
interface LiveBuildDAO {
    @Query("SELECT * FROM LiveBuild")
    fun getLiveBuilds(): Flow<List<LiveBuild>>

    @Query("SELECT * FROM LiveBuild WHERE channel = :channel")
    suspend fun getLiveBuildByChannel(channel: Channel): LiveBuild?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLiveBuild(liveBuild: LiveBuild)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(builds: List<LiveBuild>)

    @Delete
    suspend fun deleteLiveBuild(liveBuild: LiveBuild)
}