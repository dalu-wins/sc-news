package com.daluwi.sc_news.features.settings.domain.use_case

import com.daluwi.sc_news.features.settings.domain.models.Settings
import com.daluwi.sc_news.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class SetDynamicColorUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(isSet: Boolean) {
        val settings: Flow<Settings> = repository.getSettings()
        repository.setSettings(
            settings.first().copy(dynamicColors = isSet)
        )
    }
}
