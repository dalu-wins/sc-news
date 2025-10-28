package com.daluwi.sc_news.features.patches.data.source.remote

import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.models.Version
import com.daluwi.sc_news.features.patches.domain.models.Wave

fun PatchApiObject.toDomain(): Patch = Patch(
    sourceUrl = url,
    channel = getChannel(subject),
    version = getVersion(subject),
    build = getBuild(subject),
    pinned = pinned
)

private fun getChannel(subject: String): Channel {
    val upperSubject = subject.uppercase()
    return when {
        upperSubject.contains("PREVIEW") -> Channel.Preview
        upperSubject.contains("HOTFIX") -> Channel.Hotfix
        upperSubject.contains("EPTU") -> Channel.EPTU
        upperSubject.contains("PTU") -> Channel.PTU(getWave(subject))
        upperSubject.contains("LIVE") -> Channel.Live

        else -> Channel.Live
    }
}

private fun getWave(subject: String): Wave {
    val upperSubject = subject.uppercase()
    return when {
        upperSubject.contains("WAVE 1") -> Wave.One
        upperSubject.contains("WAVE 2") -> Wave.Two
        upperSubject.contains("WAVE 3") -> Wave.Three
        upperSubject.contains("WAVE 4") -> Wave.Four

        upperSubject.contains("ALL BACKER") -> Wave.AllBackers

        else -> Wave.AllBackers
    }
}

private fun getVersion(subject: String): Version {
    val versionRegex = "(\\d+)\\.(\\d+)\\.(\\d+)".toRegex()
    val match = versionRegex.find(subject)

    val major = match?.groupValues?.getOrNull(1)?.toIntOrNull() ?: 0
    val minor = match?.groupValues?.getOrNull(2)?.toIntOrNull() ?: 0
    val patchNum = match?.groupValues?.getOrNull(3)?.toIntOrNull() ?: 0

    // Sometimes version numbers are shortened to major.minor
    if (major == 0 && minor == 0 && patchNum == 0) {
        return getShortVersion(subject)
    }

    return Version(major, minor, patchNum)
}

private fun getShortVersion(subject: String): Version {
    val versionRegex = "(\\d+)\\.(\\d+)".toRegex()
    val match = versionRegex.find(subject)

    val major = match?.groupValues?.getOrNull(1)?.toIntOrNull() ?: 0
    val minor = match?.groupValues?.getOrNull(2)?.toIntOrNull() ?: 0

    return Version(major, minor, 0)
}

// TODO:    Build numbers for hotfixes are not available from thread's subject.
//          Need to load full thread...
private fun getBuild(subject: String): String {
    val buildRegex = "\\d{7,}".toRegex()

    return buildRegex.find(subject)?.value ?: ""
}