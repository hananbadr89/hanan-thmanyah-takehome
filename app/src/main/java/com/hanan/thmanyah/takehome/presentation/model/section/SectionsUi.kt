package com.hanan.thmanyah.takehome.presentation.model.section

import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionLayout
import com.hanan.thmanyah.takehome.presentation.model.card.CardUi

data class SectionsPageUi(
    val sections: List<SectionUi>,
    val paging: PagingUi?
)

data class PagingUi(
    val currentPage: Int?,
    val nextPage: String?,
    val totalPages: Int?
) {
    val canLoadMore: Boolean get() = !nextPage.isNullOrBlank()
}

data class SectionUi(
    val id: String,
    val title: String,
    val layout: SectionLayout,
    val order: Any,
    val items: List<CardUi>
)