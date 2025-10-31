package com.daluwi.sc_news.features.patches.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.UiText
import com.daluwi.sc_news.features.patches.presentation.PatchEvent

@Composable
fun PatchFAB(
    onEvent: (PatchEvent) -> Unit
) {
    ExtendedFloatingActionButton(
        text = { Text(UiText.StringResource(R.string.refresh_fab).asString()) },
        icon = { Icon(Icons.Default.Refresh, "Update list of currently live patches.") },
        onClick = { onEvent(PatchEvent.Refresh) },
    )
}