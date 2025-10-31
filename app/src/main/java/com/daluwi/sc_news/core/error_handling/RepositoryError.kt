package com.daluwi.sc_news.core.error_handling

import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.error_handling.UiText.StringResource


enum class RepositoryError : Error {
    REMOTE_FAILED,
    LOCAL_FAILED,
    NO_INTERNET
}

fun RepositoryError.asUiText(): UiText {
    return when (this) {
        RepositoryError.REMOTE_FAILED -> StringResource(R.string.remote_failed)
        RepositoryError.LOCAL_FAILED -> StringResource(R.string.local_failed)
        RepositoryError.NO_INTERNET -> StringResource(R.string.no_internet)
    }
}