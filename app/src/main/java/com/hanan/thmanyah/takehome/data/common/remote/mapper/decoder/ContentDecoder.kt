package com.hanan.thmanyah.takehome.data.common.remote.mapper.decoder

import com.hanan.thmanyah.takehome.data.common.remote.dto.SectionDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.AudioArticleContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.AudioBookContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.ContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.EpisodeContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.PodcastContentDto
import com.hanan.thmanyah.takehome.domain.common.model.model.section.ContentType
import com.hanan.thmanyah.takehome.domain.common.model.model.section.toContentType
import com.squareup.moshi.Moshi

class ContentDecoder(
    private val moshi: Moshi
) {
    private val mapAdapter = moshi.adapter(Map::class.java)

    fun decode(section: SectionDto): List<ContentDto> {
        val type = section.contentType.orEmpty().toContentType()
        val raw = section.content.orEmpty()

        return when (type) {
            ContentType.PODCAST ->
                raw.mapNotNull { decodeItem(it, PodcastContentDto::class.java) }

            ContentType.EPISODE ->
                raw.mapNotNull { decodeItem(it, EpisodeContentDto::class.java) }

            ContentType.AUDIO_BOOK ->
                raw.mapNotNull { decodeItem(it, AudioBookContentDto::class.java) }

            ContentType.AUDIO_ARTICLE ->
                raw.mapNotNull { decodeItem(it, AudioArticleContentDto::class.java) }

            ContentType.UNKNOWN -> emptyList()
        }
    }

    private fun <T : ContentDto> decodeItem(map: Map<String, Any?>, clazz: Class<T>): T? {
        val json = mapAdapter.toJson(map)
        return moshi.adapter(clazz).fromJson(json)
    }
}