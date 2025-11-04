package com.daluwi.sc_news.app.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.daluwi.sc_news.app.MainEvent
import com.daluwi.sc_news.app.MainViewModel
import com.daluwi.sc_news.app.navigation.fabs.PatchFAB

@Composable
fun AppNavigation(viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val startAppDestination = AppDestinations.Patches

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val currentRoute = currentDestination?.route

    val uriHandler = LocalUriHandler.current

    Scaffold(
        bottomBar = {
            NavigationBar {
                AppDestinations.entries.forEach { destination ->
                    val selected = currentRoute == destination.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (currentRoute != destination.route) {
                                navController.navigate(destination.route) {
                                    // PopUpTo prevents stack-trash from happening
                                    popUpTo(startAppDestination.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selected) {
                                    destination.selectedIcon
                                } else {
                                    destination.unselectedIcon
                                },
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label.asString()) }
                    )
                }
            }
        },
        floatingActionButton = {
            val fabVisible = currentRoute == AppDestinations.Patches.route
            AnimatedVisibility(
                visible = fabVisible,
                enter = slideIn(
                    initialOffset = {
                        IntOffset(
                            y = 0,
                            x = 600
                        )
                    }
                ),
                exit = slideOut(
                    targetOffset = {
                        IntOffset(
                            y = 0,
                            x = 600
                        )
                    }
                ),
            ) {
                PatchFAB(onClick = {
                    viewModel.onEvent(
                        MainEvent.VisitSpectrum(uriHandler)
                    )
                })
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController = navController,
            startAppDestinations = startAppDestination,
            modifier = Modifier.padding(contentPadding),
        )
    }
}