package com.daluwi.sc_newshub.app.di

import android.app.Application
import androidx.room.Room
import com.daluwi.sc_newshub.data.repository.LiveBuildRepositoryImpl
import com.daluwi.sc_newshub.data.source.LiveBuildDatabase
import com.daluwi.sc_newshub.domain.repository.LiveBuildRepository
import com.daluwi.sc_newshub.domain.use_case.GetLiveBuildsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLiveBuildDatabase(app: Application): LiveBuildDatabase {
        return Room.databaseBuilder(
            app,
            LiveBuildDatabase::class.java,
            LiveBuildDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLiveBuildRepository(db: LiveBuildDatabase): LiveBuildRepository {
        return LiveBuildRepositoryImpl(db.liveBuildDAO)
    }

    @Provides
    @Singleton
    fun provideGetLiveBuildsUseCase(repository: LiveBuildRepository): GetLiveBuildsUseCase {
        return GetLiveBuildsUseCase(repository)
    }

}