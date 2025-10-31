package com.daluwi.sc_news.features.patches.presentation

import androidx.compose.ui.platform.UriHandler
import com.daluwi.sc_news.core.error_handling.UiText

sealed class PatchEvent {
    object Refresh : PatchEvent()
    data class VisitSpectrum(val uriHandler: UriHandler) : PatchEvent()
    data class VisitThread(val uriHandler: UriHandler, val threadUrl: String) : PatchEvent()
    data class Error(val message: UiText) : PatchEvent()
}