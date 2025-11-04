package com.daluwi.sc_news.app.navigation.fabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.UiText

@Composable
fun PatchFAB(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { Text(UiText.StringResource(R.string.spectrum_button).asString()) },
        icon = { Icon(Icons.Default.Web, "See all patch notes on Spectrum.") },
        onClick = onClick
    )
}