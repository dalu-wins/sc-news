package com.daluwi.sc_newshub.features.settings.domain.repository

import com.daluwi.sc_newshub.features.settings.domain.models.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setSettings(settings: Settings)
    fun getSettings(): Flow<Settings>
}