package com.daluwi.sc_newshub.features.patches.domain.models

sealed class Channel {
    object Live : Channel()
    class PTU(val wave: Wave) : Channel()
    object EPTU : Channel()
    object Hotfix : Channel()
    object Preview : Channel()

    override fun toString(): String = when (this) {
        is Live -> "Live"
        is PTU -> "PTU"
        is EPTU -> "EPTU"
        is Hotfix -> "Hotfix"
        is Preview -> "Preview"
    }
}
