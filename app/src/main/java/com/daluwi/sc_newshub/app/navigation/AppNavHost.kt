package com.daluwi.sc_newshub.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daluwi.sc_newshub.features.patches.presentation.PatchScreen
import com.daluwi.sc_newshub.features.settings.presentation.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startAppDestinations: AppDestinations,
    modifier: Modifier,
) {
    NavHost(
        navController,
        startDestination = startAppDestinations.route,
        modifier = modifier
    ) {
        AppDestinations.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    AppDestinations.Patches -> PatchScreen()
                    AppDestinations.Settings -> SettingsScreen()
                }
            }
        }
    }
}