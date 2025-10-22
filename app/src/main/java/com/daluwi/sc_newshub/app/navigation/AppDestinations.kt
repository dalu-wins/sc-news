package com.daluwi.sc_newshub.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppDestinations(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val contentDescription: String
) {
    Patches(
        "patches",
        "Patch Notes",
        Icons.AutoMirrored.Filled.Notes,
        Icons.AutoMirrored.Outlined.Notes,
        "List of latest patches."
    ),
    Settings(
        "settings",
        "Settings",
        Icons.Filled.Settings,
        Icons.Outlined.Settings,
        "Application settings."
    ),
}