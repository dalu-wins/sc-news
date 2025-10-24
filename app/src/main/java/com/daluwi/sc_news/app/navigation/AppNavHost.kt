package com.daluwi.sc_news.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daluwi.sc_news.features.patches.presentation.PatchScreen
import com.daluwi.sc_news.features.settings.presentation.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startAppDestinations: AppDestinations,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startAppDestinations.route,
        modifier = modifier
    ) {
        AppDestinations.entries.forEach { destination ->
            composable(destination.route) { backStackEntry ->
                when (destination) {
                    AppDestinations.Patches -> {
                        PatchScreen()
                    }

                    AppDestinations.Settings -> {
                        SettingsScreen()
                    }

                }
            }
        }
    }
}