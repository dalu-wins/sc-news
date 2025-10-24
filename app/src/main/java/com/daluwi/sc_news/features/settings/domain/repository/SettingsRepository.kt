package com.daluwi.sc_news.features.settings.domain.repository

import com.daluwi.sc_news.features.settings.domain.models.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setSettings(settings: Settings)
    fun getSettings(): Flow<Settings>
}