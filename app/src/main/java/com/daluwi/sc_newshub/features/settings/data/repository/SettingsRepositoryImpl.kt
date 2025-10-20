package com.daluwi.sc_newshub.features.settings.data.repository

import com.daluwi.sc_newshub.features.settings.data.source.SettingsDataStore
import com.daluwi.sc_newshub.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: SettingsDataStore
) : SettingsRepository {
    override val useDynamicColors: Flow<Boolean> = dataStore.useDynamicColors

    override suspend fun updateUseDynamicColors(enabled: Boolean) {
        dataStore.setUseDynamicColors(enabled)
    }
}
