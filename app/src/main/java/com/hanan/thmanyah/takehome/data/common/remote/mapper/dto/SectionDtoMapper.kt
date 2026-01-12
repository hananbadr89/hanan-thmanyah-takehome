package com.hanan.thmanyah.takehome.data.common.remote.mapper.dto

import com.hanan.thmanyah.takehome.data.home.local.model.PagingState
import com.hanan.thmanyah.takehome.data.common.remote.dto.SectionDto
import com.hanan.thmanyah.takehome.data.common.remote.mapper.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.data.common.remote.dto.SectionsResponseDto
import com.hanan.thmanyah.takehome.domain.common.model.model.section.ContentType
import com.hanan.thmanyah.takehome.domain.common.model.model.section.Section
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionLayout
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.common.model.model.section.toContentType
import com.hanan.thmanyah.takehome.domain.common.model.model.section.toSectionLayout
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

    val pagingState = PagingState(
        currentPage = 1,
        nextPage = pagination?.nextPage?.takeIf { it.isNotBlank() },
        totalPages = pagination?.totalPages ?: 1,
        updatedAt = System.currentTimeMillis()
    )

    return SectionsPage(
        sections = sections,
       paging = pagingState
    )
}

fun SectionDto.sectionKey(): String {
    val safeName = (name ?: "")
        .trim()
        .lowercase()
        .replace(Regex("\\s+"), " ")
        .replace(Regex("[^a-z0-9 ]"), "")

    return listOf(
        contentType.orEmpty(),
        type.orEmpty(),
        order?.toString().orEmpty(),
        safeName
    ).joinToString("|")
}

private fun SectionDto.stableSectionId(): String =
    "home_${order ?: -1}_${type.orEmpty()}_${contentType.orEmpty()}"
