package com.daluwi.sc_news.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.UiText

enum class AppDestinations(
    val route: String,
    val label: UiText,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val contentDescription: String
) {
    Patches(
        "patches",
        UiText.StringResource(R.string.nav_patch_notes),
        Icons.AutoMirrored.Filled.Notes,
        Icons.AutoMirrored.Outlined.Notes,
        "List of latest patches."
    ),
    Settings(
        "settings",
        UiText.StringResource(R.string.nav_settings),
        Icons.Filled.Settings,
        Icons.Outlined.Settings,
        "Application settings."
    ),
}