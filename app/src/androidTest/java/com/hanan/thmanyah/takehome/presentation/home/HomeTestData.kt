package com.hanan.thmanyah.takehome.presentation.home

import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionLayout
import com.hanan.thmanyah.takehome.presentation.model.card.SquareCardUi
import com.hanan.thmanyah.takehome.presentation.model.section.PagingUi
import com.hanan.thmanyah.takehome.presentation.model.section.SectionUi
import com.hanan.thmanyah.takehome.presentation.model.section.SectionsPageUi

object HomeTestData {

    fun homePage(
        sectionTitle: String = "Section",
        cardTitle: String = "Card",
        canLoadMore: Boolean = true,
        sectionCount: Int = 1,
    ): SectionsPageUi {
        val sections = (1..sectionCount).map { i ->
            SectionUi(
                id = "s$i",
                title = "$sectionTitle $i",
                layout = SectionLayout.SQUARE,
                order = i,
                items = listOf(
                    SquareCardUi(
                        id = "c$i",
                        composeKey = "c$i",
                        title = "$cardTitle $i",
                        subtitle = "Sub $i",
                        imageUrl = ""
                    )
                )
            )
        }

        return SectionsPageUi(
            sections = sections,
            paging = PagingUi(
                currentPage = 1,
                nextPage = if (canLoadMore) "2" else null,
                totalPages = 10
            )
        )
    }
}