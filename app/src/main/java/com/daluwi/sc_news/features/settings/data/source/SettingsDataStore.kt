package com.daluwi.sc_news.features.settings.data.source

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.daluwi.sc_news.features.settings.domain.models.Settings
import com.materialkolor.ktx.toHex
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
        private val SHOW_BUILD = booleanPreferencesKey("show_build")
        private val THEME_COLOR = stringPreferencesKey("theme_color")
    }

    val settingsFlow: Flow<Settings> = context.dataStore.data.map { prefs ->
        Settings(
            dynamicColors = prefs[DYNAMIC_COLORS] ?: true,
            build = prefs[SHOW_BUILD] ?: false,
            colorHex = prefs[THEME_COLOR] ?: Color.Blue.toHex()
        )
    }

    suspend fun setDynamicColors(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DYNAMIC_COLORS] = enabled
        }
    }

    suspend fun setThemeColors(colorHex: String) {
        context.dataStore.edit { prefs ->
            prefs[THEME_COLOR] = colorHex
        }
    }

    suspend fun setBuildSetting(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[SHOW_BUILD] = enabled
        }
    }
}