package com.daluwi.sc_newshub.core.di

import android.app.Application
import androidx.room.Room
import com.daluwi.sc_newshub.features.builds.data.repository.BuildRepositoryImpl
import com.daluwi.sc_newshub.features.builds.data.source.BuildDatabase
import com.daluwi.sc_newshub.features.builds.domain.repository.BuildRepository
import com.daluwi.sc_newshub.features.builds.domain.use_case.BuildUseCases
import com.daluwi.sc_newshub.features.builds.domain.use_case.GetBuildsUseCase
import com.daluwi.sc_newshub.features.builds.domain.use_case.PrepopulateDBUseCase
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
    fun provideLiveBuildDatabase(app: Application): BuildDatabase {
        return Room.databaseBuilder(
            app,
            BuildDatabase::class.java,
            BuildDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLiveBuildRepository(db: BuildDatabase): BuildRepository {
        return BuildRepositoryImpl(db.buildDAO)
    }

    @Provides
    @Singleton
    fun provideLiveBuildUseCases(repository: BuildRepository): BuildUseCases {
        return BuildUseCases(
            getBuildsUseCase = GetBuildsUseCase(repository),
            prepopulateDBUseCase = PrepopulateDBUseCase(repository),
        )
    }

}