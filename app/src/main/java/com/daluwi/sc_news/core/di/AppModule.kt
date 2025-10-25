package com.daluwi.sc_news.core.di

import android.app.Application
import androidx.room.Room
import com.daluwi.sc_news.features.patches.data.repository.PatchRepositoryImpl
import com.daluwi.sc_news.features.patches.data.source.PatchDatabase
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository
import com.daluwi.sc_news.features.patches.domain.use_case.GetPatchesUseCase
import com.daluwi.sc_news.features.patches.domain.use_case.PatchUseCases
import com.daluwi.sc_news.features.patches.domain.use_case.RefreshUseCase
import com.daluwi.sc_news.features.settings.data.repository.SettingsRepositoryImpl
import com.daluwi.sc_news.features.settings.data.source.SettingsDataStore
import com.daluwi.sc_news.features.settings.domain.repository.SettingsRepository
import com.daluwi.sc_news.features.settings.domain.use_case.GetDynamicColorUseCase
import com.daluwi.sc_news.features.settings.domain.use_case.SetDynamicColorUseCase
import com.daluwi.sc_news.features.settings.domain.use_case.SettingsUseCases
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
    fun providePatchDatabase(app: Application): PatchDatabase {
        return Room.databaseBuilder(
            app,
            PatchDatabase::class.java,
            PatchDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePatchRepository(db: PatchDatabase): PatchRepository {
        return PatchRepositoryImpl(db.patchDAO)
    }

    @Provides
    @Singleton
    fun providePatchUseCases(repository: PatchRepository): PatchUseCases {
        return PatchUseCases(
            getPatchesUseCase = GetPatchesUseCase(repository),
            refreshUseCase = RefreshUseCase(repository),
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

    @Provides
    @Singleton
    fun provideSettingsUseCases(repository: SettingsRepository): SettingsUseCases {
        return SettingsUseCases(
            setDynamicColorUseCase = SetDynamicColorUseCase(repository),
            getDynamicColorUseCase = GetDynamicColorUseCase(repository),
        )
    }

}