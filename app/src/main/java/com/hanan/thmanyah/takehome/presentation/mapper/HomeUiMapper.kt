package com.hanan.thmanyah.takehome.presentation.mapper

import com.hanan.thmanyah.takehome.domain.common.model.model.item.HomeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.section.Section
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionLayout
import com.hanan.thmanyah.takehome.presentation.model.card.CardUi
import com.hanan.thmanyah.takehome.presentation.model.section.PagingUi
import com.hanan.thmanyah.takehome.presentation.model.section.SectionUi
import com.hanan.thmanyah.takehome.presentation.model.section.SectionsPageUi

fun SectionsPage.toUi(): SectionsPageUi =
    SectionsPageUi(
        sections = sections.mapNotNull { it.toUiOrNull() },
        paging = PagingUi(
            currentPage = paging?.currentPage,
            nextPage = paging?.nextPage,
            totalPages = paging?.totalPages
        )
    )

fun Section.toUiOrNull(): SectionUi? {
    val uiItems = items.mapIndexedNotNull { index, item ->
        item.toLayoutUi(
            layout = layout,
            composeKey = "${id}_${item.id}_index_$index"
        )
    }
    if (uiItems.isEmpty()) return null


    return SectionUi(
        id = id,
        title = title,
        layout = layout,
        order = order,
        items = uiItems
    )
}

private fun HomeItem.toLayoutUi(layout: SectionLayout, composeKey: String): CardUi? {
    return when (layout) {
        SectionLayout.SQUARE -> toSquareUi(composeKey)
        SectionLayout.QUEUE -> toQueueUi(composeKey)
        SectionLayout.TWO_LINES_GRID -> toGridUi(composeKey)
        SectionLayout.BIG_SQUARE -> toBigSquareUi(composeKey)
        SectionLayout.UNKNOWN -> null
    }
}