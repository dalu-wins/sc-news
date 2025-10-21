package com.daluwi.sc_newshub.features.settings.domain.use_case

import com.daluwi.sc_newshub.features.settings.domain.repository.SettingsRepository

class SetDynamicColorUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(isSet: Boolean) {
        repository.setDynamicColors(isSet)
    }
}