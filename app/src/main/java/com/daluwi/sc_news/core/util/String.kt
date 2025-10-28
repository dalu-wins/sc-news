package com.daluwi.sc_news.core.util

private fun String.containsAny(keywords: List<String>): Boolean {
    val upperThis = this.uppercase()
    return keywords.any { upperThis.contains(it.uppercase()) }
}