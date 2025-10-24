package com.daluwi.sc_news.features.settings.data.repository

import com.daluwi.sc_news.features.settings.data.source.SettingsDataStore
import com.daluwi.sc_news.features.settings.domain.models.Settings
import com.daluwi.sc_news.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: SettingsDataStore
) : SettingsRepository {

    override suspend fun setSettings(settings: Settings) {
        dataStore.setDynamicColors(settings.dynamicColors)
    }

    override fun getSettings(): Flow<Settings> {
        return dataStore.settingsFlow
    }
}
