package com.daluwi.sc_newshub.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    Live("live", "Live", Icons.Default.Gamepad, "Game builds that are currently online."),
    Settings("settings", "Settings", Icons.Default.Settings, "Application settings."),
}