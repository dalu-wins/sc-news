package com.daluwi.sc_news.features.patches.data.source.local

import com.daluwi.sc_news.features.patches.domain.models.Patch

fun PatchEntity.toDomain(): Patch = Patch(
    sourceUrl = sourceUrl,
    channel = channel,
    version = version,
    build = build,
    pinned = pinned,
    currentlyOnline = currentlyOnline
)

fun Patch.toEntity(): PatchEntity = PatchEntity(
    sourceUrl = sourceUrl,
    channel = channel,
    version = version,
    build = build,
    pinned = pinned,
    currentlyOnline = currentlyOnline
)