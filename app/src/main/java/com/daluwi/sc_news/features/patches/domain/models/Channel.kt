package com.daluwi.sc_news.features.patches.domain.models

sealed class Channel {
    object Live : Channel()
    class PTU(val wave: Wave) : Channel()
    object EPTU : Channel()
    object Hotfix : Channel()
    object Preview : Channel()
    object Unknown : Channel()
}
