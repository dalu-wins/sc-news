package com.daluwi.sc_newshub.core.di

import android.app.Application
import androidx.room.Room
import com.daluwi.sc_newshub.features.patches.data.repository.PatchRepositoryImpl
import com.daluwi.sc_newshub.features.patches.data.source.BuildDatabase
import com.daluwi.sc_newshub.features.patches.domain.repository.PatchRepository
import com.daluwi.sc_newshub.features.patches.domain.use_case.GetPatchesUseCase
import com.daluwi.sc_newshub.features.patches.domain.use_case.PatchUseCases
import com.daluwi.sc_newshub.features.patches.domain.use_case.PrepopulateDBUseCase
import com.daluwi.sc_newshub.features.settings.data.repository.SettingsRepositoryImpl
import com.daluwi.sc_newshub.features.settings.data.source.SettingsDataStore
import com.daluwi.sc_newshub.features.settings.domain.repository.SettingsRepository
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
    fun provideLiveBuildRepository(db: BuildDatabase): PatchRepository {
        return PatchRepositoryImpl(db.buildDAO)
    }

    @Provides
    @Singleton
    fun provideLiveBuildUseCases(repository: PatchRepository): PatchUseCases {
        return PatchUseCases(
            getPatchesUseCase = GetPatchesUseCase(repository),
            prepopulateDBUseCase = PrepopulateDBUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(app: Application): SettingsDataStore {
        return SettingsDataStore(app)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(ds: SettingsDataStore): SettingsRepository {
        return SettingsRepositoryImpl(ds)
    }
    
}