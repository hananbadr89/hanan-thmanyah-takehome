package com.hanan.thmanyah.takehome.data.home.mapper.dto

import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionDto
import com.hanan.thmanyah.takehome.data.home.mapper.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionsResponseDto
import com.hanan.thmanyah.takehome.domain.home.model.section.ContentType
import com.hanan.thmanyah.takehome.domain.home.model.section.Section
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionLayout
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.home.model.section.toContentType
import com.hanan.thmanyah.takehome.domain.home.model.section.toSectionLayout
import kotlin.collections.mapNotNull
import kotlin.collections.orEmpty

fun SectionDto.toDomain(
    decoder: ContentDecoder
): Section? {

    val layout = type.orEmpty().toSectionLayout()
    val contentType = contentType.orEmpty().toContentType()

    if (layout == SectionLayout.UNKNOWN || contentType == ContentType.UNKNOWN) return null

    val items = decoder.decode(this)
        .mapNotNull { it.toDomainItem() }

    if (items.isEmpty()) return null

    return Section(
        id = stableSectionId(),
        title = name.orEmpty(),
        layout = layout,
        contentType = contentType,
        order = order ?: Int.MAX_VALUE,
        items = items
    )
}

fun SectionsResponseDto.toDomainPage(
    decoder: ContentDecoder
): SectionsPage {
    val sections = sections
        .orEmpty()
        .mapNotNull { it.toDomain(decoder) }
        .sortedBy { it.order }

    return SectionsPage(sections)
}

private fun SectionDto.stableSectionId(): String =
    "home_${order ?: -1}_${type.orEmpty()}_${contentType.orEmpty()}"
