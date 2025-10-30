package com.daluwi.sc_news.core.di

import android.app.Application
import androidx.room.Room
import com.daluwi.sc_news.core.connectivity.NetworkChecker
import com.daluwi.sc_news.core.connectivity.NetworkCheckerImpl
import com.daluwi.sc_news.features.patches.data.repository.PatchRepositoryImpl
import com.daluwi.sc_news.features.patches.data.source.local.PatchDatabase
import com.daluwi.sc_news.features.patches.data.source.remote.PatchApi
import com.daluwi.sc_news.features.patches.domain.repository.PatchRepository
import com.daluwi.sc_news.features.patches.domain.use_case.GetLocalPatches
import com.daluwi.sc_news.features.patches.domain.use_case.GetUpToDatePatches
import com.daluwi.sc_news.features.patches.domain.use_case.PatchUseCases
import com.daluwi.sc_news.features.patches.domain.use_case.RefreshPatches
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
    fun providePatchApi(): PatchApi {
        return PatchApi()
    }

    @Provides
    @Singleton
    fun providePatchRepository(
        db: PatchDatabase,
        api: PatchApi,
        networkChecker: NetworkChecker
    ): PatchRepository {
        return PatchRepositoryImpl(db.patchDAO, api, networkChecker)
    }

    @Provides
    @Singleton
    fun providePatchUseCases(repository: PatchRepository): PatchUseCases {
        return PatchUseCases(
            getUpToDatePatches = GetUpToDatePatches(repository),
            refreshPatches = RefreshPatches(repository),
            getLocalPatches = GetLocalPatches(repository)
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

    @Provides
    @Singleton
    fun provideNetworkChecker(app: Application): NetworkChecker {
        return NetworkCheckerImpl(app)
    }

}