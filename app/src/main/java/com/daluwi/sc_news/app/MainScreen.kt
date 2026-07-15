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
    val themeColor = state.themeColor

    LaunchedEffect(dynamicColors, themeColor) {
        if (dynamicColors != null && themeColor != null) {
            deactivateSplashScreen()
        }
    }

    if (dynamicColors != null && themeColor != null) {
        SCNewsTheme(
            dynamicColor = dynamicColors,
            themeColor = themeColor
        ) {
            AppNavigation()
        }
    }
}