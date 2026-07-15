package com.daluwi.sc_news.features.settings.domain.models

import androidx.compose.ui.graphics.Color
import com.materialkolor.ktx.toHex

data class Settings(
    var dynamicColors: Boolean = true,
    var build: Boolean = false,
    var colorHex: String = Color.Blue.toHex(),
)
