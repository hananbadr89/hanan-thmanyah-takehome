package com.hanan.thmanyah.takehome.presentation.home.mapper

import com.hanan.thmanyah.takehome.domain.home.model.item.HomeItem
import com.hanan.thmanyah.takehome.domain.home.model.section.Section
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionLayout
import com.hanan.thmanyah.takehome.presentation.home.model.HomeCardUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomePagingUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionsPageUi

fun SectionsPage.toUi(): HomeSectionsPageUi =
    HomeSectionsPageUi(
        sections = sections.mapNotNull { it.toUiOrNull() },
        paging = HomePagingUi(
            currentPage = paging.currentPage,
            nextPage = paging.nextPage,
            totalPages = paging.totalPages
        )
    )

fun Section.toUiOrNull(): HomeSectionUi? {
    val uiItems = items.mapIndexedNotNull { index, item ->
        item.toLayoutUi(
            layout = layout,
            composeKey = "${id}_${item.id}_index_$index"
        )
    }
    if (uiItems.isEmpty()) return null


    return HomeSectionUi(
        id = id,
        title = title,
        layout = layout,
        order = order,
        items = uiItems
    )
}

private fun HomeItem.toLayoutUi(layout: SectionLayout, composeKey: String): HomeCardUi? {
    return when (layout) {
        SectionLayout.SQUARE -> toSquareUi(composeKey)
        SectionLayout.QUEUE -> toQueueUi(composeKey)
        SectionLayout.TWO_LINES_GRID -> toGridUi(composeKey)
        SectionLayout.BIG_SQUARE -> toBigSquareUi(composeKey)
        SectionLayout.UNKNOWN -> null
    }
}