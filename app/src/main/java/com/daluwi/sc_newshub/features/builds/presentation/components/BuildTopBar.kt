package com.daluwi.sc_newshub.features.builds.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildTopBar() {
    TopAppBar(
        title = { Text("Currently Online") },
    )
}