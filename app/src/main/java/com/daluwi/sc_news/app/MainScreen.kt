package com.daluwi.sc_news.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_news.app.navigation.AppNavigation
import com.daluwi.sc_news.core.theme.SCNewsTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    deactivateSplashScreen: () -> Unit
) {

    val state = viewModel.state.value

    val dynamicColors = state.dynamicColors

    LaunchedEffect(dynamicColors) {
        if (dynamicColors != null) {
            deactivateSplashScreen()
        }
    }

    dynamicColors?.let { dc ->
        SCNewsTheme(dynamicColor = dc) {
            AppNavigation()
        }
    }
}