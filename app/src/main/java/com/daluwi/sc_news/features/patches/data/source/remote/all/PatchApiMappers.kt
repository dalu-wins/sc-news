package com.daluwi.sc_news.features.patches.data.source.remote.all

import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.models.Version
import com.daluwi.sc_news.features.patches.domain.models.Wave

fun PatchApiObject.toDomain(): Patch = Patch(
    sourceUrl = sourceUrl,
    channel = parseChannel(channel, wave),
    version = parseVersion(version),
    build = build,
    pinned = pinned,
    currentlyOnline = currentlyOnline
)

private fun parseChannel(channel: String?, wave: String?): Channel {
    return when (channel?.uppercase()) {
        "PREVIEW" -> Channel.Preview
        "HOTFIX" -> Channel.Hotfix
        "EPTU" -> Channel.EPTU
        "PTU" -> Channel.PTU(parseWave(wave))
        "LIVE" -> Channel.Live
        else -> Channel.Unknown
    }
}

private fun parseWave(wave: String?): Wave {
    return when (wave?.uppercase()) {
        "WAVE 1" -> Wave.One
        "WAVE 2" -> Wave.Two
        "WAVE 3" -> Wave.Three
        "WAVE 4" -> Wave.Four
        "ALL BACKERS" -> Wave.AllBackers
        else -> Wave.Unknown
    }
}

private fun parseVersion(apiVersion: ApiVersion?): Version {
    return Version(
        major = apiVersion?.major ?: 0,
        minor = apiVersion?.minor ?: 0,
        patch = apiVersion?.patch ?: 0
    )
}
