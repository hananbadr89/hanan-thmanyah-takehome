package com.hanan.thmanyah.takehome.data.home.mapper

import com.hanan.thmanyah.takehome.data.home.dto.SectionDto
import com.hanan.thmanyah.takehome.data.home.dto.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.domain.home.model.section.ContentType
import com.hanan.thmanyah.takehome.domain.home.model.section.Section
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionLayout
import com.hanan.thmanyah.takehome.domain.home.model.section.toContentType
import com.hanan.thmanyah.takehome.domain.home.model.section.toSectionLayout

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

private fun SectionDto.stableSectionId(): String =
    "home_${order ?: -1}_${type.orEmpty()}_${contentType.orEmpty()}"
