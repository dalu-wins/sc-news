package com.daluwi.sc_news.core.error_handling

sealed interface UserEvent {
    data class Error(val message: UiText) : UserEvent
}