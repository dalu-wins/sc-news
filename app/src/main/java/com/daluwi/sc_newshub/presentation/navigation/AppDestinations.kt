package com.daluwi.sc_newshub.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Looks3
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material.icons.filled.LooksTwo
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlaylistAddCircle
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    One("one", "One", Icons.Default.LooksOne, "One"),
    Two("two", "Two", Icons.Default.LooksTwo, "Two"),
    Three("three", "Three", Icons.Default.Looks3, "Three")
}