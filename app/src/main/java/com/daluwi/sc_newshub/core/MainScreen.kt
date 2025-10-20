package com.daluwi.sc_newshub.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_newshub.core.navigation.AppNavigation
import com.daluwi.sc_newshub.core.theme.SCNewsHubTheme
import com.daluwi.sc_newshub.features.settings.presentation.SettingsViewModel

@Composable
fun MainScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.state
    SCNewsHubTheme(dynamicColor = state.useDynamicColors) {
        AppNavigation()
    }
}