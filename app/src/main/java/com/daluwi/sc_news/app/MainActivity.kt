package com.daluwi.sc_news.app

import android.content.res.Configuration
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.daluwi.sc_news.features.settings.domain.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val isDarkTheme = (nightModeFlags == Configuration.UI_MODE_NIGHT_YES)

        val lightTransparentStyle =
            SystemBarStyle.light(scrim = TRANSPARENT, darkScrim = TRANSPARENT)
        val darkTransparentStyle = SystemBarStyle.dark(scrim = TRANSPARENT)
        val systemBarStyle = if (isDarkTheme) darkTransparentStyle else lightTransparentStyle

        enableEdgeToEdge(statusBarStyle = systemBarStyle, navigationBarStyle = systemBarStyle)

        var keepSplash = true
        splashScreen.setKeepOnScreenCondition { keepSplash }

        lifecycleScope.launch {
            settingsRepository.getSettings().first()
            keepSplash = false
            setContent {
                MainScreen()
            }
        }
    }
}
