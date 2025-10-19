package com.daluwi.sc_newshub.features.builds.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BuildFAB() {
    ExtendedFloatingActionButton(
        text = { Text("Refresh") },
        icon = { Icon(Icons.Default.Refresh, "Update list of currently live builds.") },
        onClick = { /***/ },
    )
}