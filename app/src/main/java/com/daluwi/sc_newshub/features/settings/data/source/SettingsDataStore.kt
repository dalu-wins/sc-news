package com.daluwi.sc_newshub.features.settings.data.source

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.daluwi.sc_newshub.features.settings.domain.models.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("user_settings")

@Singleton
class SettingsDataStore @Inject constructor(
    private val context: Context
) {

    companion object {
        private val DYNAMIC_COLORS = booleanPreferencesKey("dynamic_colors")
    }

    val settingsFlow: Flow<Settings> = context.dataStore.data.map { prefs ->
        Settings(dynamicColors = prefs[DYNAMIC_COLORS] ?: true)
    }

    suspend fun setDynamicColors(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DYNAMIC_COLORS] = enabled
        }
    }
}