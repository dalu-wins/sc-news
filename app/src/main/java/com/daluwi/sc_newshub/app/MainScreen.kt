package com.daluwi.sc_newshub.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_newshub.app.navigation.AppNavigation
import com.daluwi.sc_newshub.core.theme.SCNewsHubTheme

@Composable
fun MainScreen(
    appViewModel: MainViewModel = hiltViewModel()
) {
    val settings by appViewModel.settings.collectAsState()

    SCNewsHubTheme(dynamicColor = settings.dynamicColors) {
        AppNavigation()
    }
}