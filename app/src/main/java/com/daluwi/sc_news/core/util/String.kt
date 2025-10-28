package com.daluwi.sc_news.core.util

// TODO use in Channel parsing or make api provide reliable channel info
private fun String.containsAny(keywords: List<String>): Boolean {
    val upperThis = this.uppercase()
    return keywords.any { upperThis.contains(it.uppercase()) }
}