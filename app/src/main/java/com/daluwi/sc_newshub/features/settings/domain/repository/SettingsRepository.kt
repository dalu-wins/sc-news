package com.daluwi.sc_newshub.features.settings.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val useDynamicColors: Flow<Boolean>
    suspend fun updateUseDynamicColors(enabled: Boolean)
}