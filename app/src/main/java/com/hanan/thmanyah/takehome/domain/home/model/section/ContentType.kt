package com.hanan.thmanyah.takehome.domain.home.model.section

enum class ContentType {
    PODCAST,
    EPISODE,
    AUDIO_BOOK,
    AUDIO_ARTICLE,
    UNKNOWN
}

fun String.toContentType(): ContentType =
    when (trim().lowercase()) {
        "podcast" -> ContentType.PODCAST
        "episode" -> ContentType.EPISODE
        "audio_book" -> ContentType.AUDIO_BOOK
        "audio_article" -> ContentType.AUDIO_ARTICLE
        else -> ContentType.UNKNOWN
    }