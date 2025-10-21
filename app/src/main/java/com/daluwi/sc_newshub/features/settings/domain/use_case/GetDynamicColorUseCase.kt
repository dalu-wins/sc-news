package com.daluwi.sc_newshub.features.settings.domain.use_case

import com.daluwi.sc_newshub.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetDynamicColorUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return repository.getDynamicColors()
    }
}