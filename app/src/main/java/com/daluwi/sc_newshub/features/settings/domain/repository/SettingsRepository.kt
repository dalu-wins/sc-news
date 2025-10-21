package com.daluwi.sc_newshub.features.settings.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setDynamicColors(enabled: Boolean)
    suspend fun getDynamicColors(): Flow<Boolean>
}