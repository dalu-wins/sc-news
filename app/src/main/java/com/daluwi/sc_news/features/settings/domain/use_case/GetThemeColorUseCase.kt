package com.daluwi.sc_news.features.settings.domain.use_case

import com.daluwi.sc_news.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetThemeColorUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<String> {
        return repository.getSettings().map { it.colorHex }
    }
}