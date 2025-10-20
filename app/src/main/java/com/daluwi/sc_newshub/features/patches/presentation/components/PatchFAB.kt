package com.daluwi.sc_newshub.features.patches.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PatchFAB() {
    ExtendedFloatingActionButton(
        text = { Text("Refresh") },
        icon = { Icon(Icons.Default.Refresh, "Update list of currently live patches.") },
        onClick = { /***/ },
    )
}