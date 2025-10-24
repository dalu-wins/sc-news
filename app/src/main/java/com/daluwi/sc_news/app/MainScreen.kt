package com.daluwi.sc_news.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_news.app.navigation.AppNavigation
import com.daluwi.sc_news.core.theme.SCNewsTheme

@Composable
fun MainScreen(
    appViewModel: MainViewModel = hiltViewModel()
) {
    val settings by appViewModel.settings.collectAsState()

    SCNewsTheme(dynamicColor = settings.dynamicColors) {
        AppNavigation()
    }
}