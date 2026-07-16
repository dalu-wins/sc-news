package com.daluwi.sc_news.features.settings.domain.models

import com.daluwi.sc_news.core.theme.BLUE
import com.materialkolor.ktx.toHex

data class Settings(
    var dynamicColors: Boolean = true,
    var build: Boolean = false,
    var colorHex: String = BLUE.toHex(),
)
