package com.daluwi.sc_newshub.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daluwi.sc_newshub.features.patches.presentation.PatchScreen
import com.daluwi.sc_newshub.features.settings.presentation.SettingsScreen

const val ROOT_GRAPH_ROUTE = "root_graph"

@Composable
fun AppNavHost(
    navController: NavHostController,
    startAppDestinations: AppDestinations,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startAppDestinations.route,
        route = ROOT_GRAPH_ROUTE,
        modifier = modifier
    ) {
        AppDestinations.entries.forEach { destination ->
            composable(destination.route) { backStackEntry ->
                when (destination) {
                    AppDestinations.Patches -> {
                        val parentEntry = remember(backStackEntry) {
                            navController.getBackStackEntry(ROOT_GRAPH_ROUTE)
                        }
                        PatchScreen(parentEntry)
                    }

                    AppDestinations.Settings -> {
                        val parentEntry = remember(backStackEntry) {
                            navController.getBackStackEntry(ROOT_GRAPH_ROUTE)
                        }
                        SettingsScreen(parentEntry)
                    }
                    
                }
            }
        }
    }
}