package com.daluwi.sc_newshub.features.settings.data.source

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("user_settings")

@Singleton
class SettingsDataStore @Inject constructor(
    private val context: Context
) {

    companion object {
        private val USE_DYNAMIC_COLORS = booleanPreferencesKey("use_dynamic_colors")
    }

    val useDynamicColors = context.dataStore.data.map { prefs ->
        prefs[USE_DYNAMIC_COLORS] ?: true
    }

    suspend fun setUseDynamicColors(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[USE_DYNAMIC_COLORS] = enabled
        }
    }
}