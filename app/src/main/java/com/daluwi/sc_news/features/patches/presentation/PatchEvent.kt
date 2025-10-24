package com.daluwi.sc_news.features.patches.presentation

import androidx.compose.ui.platform.UriHandler

sealed class PatchEvent {
    object Refresh : PatchEvent()
    data class VisitSpectrum(val uriHandler: UriHandler) : PatchEvent()
    data class VisitThread(val uriHandler: UriHandler, val threadUrl: String) : PatchEvent()
}