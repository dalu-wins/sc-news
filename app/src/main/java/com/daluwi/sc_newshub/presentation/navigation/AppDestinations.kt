package com.daluwi.sc_newshub.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlaylistAddCircle
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    One("one", "One", Icons.Default.MusicNote, "One"),
    Two("two", "Two", Icons.Default.Album, "Two"),
    Three("three", "Three", Icons.Default.PlaylistAddCircle, "Three")
}