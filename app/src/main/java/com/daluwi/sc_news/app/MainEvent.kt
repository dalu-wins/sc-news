package com.daluwi.sc_news.app

import androidx.compose.ui.platform.UriHandler

sealed class MainEvent {
    data class VisitSpectrum(val uriHandler: UriHandler) : MainEvent()
}