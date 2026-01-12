package com.hanan.thmanyah.takehome.presentation.home.model

import com.hanan.thmanyah.takehome.domain.home.model.section.SectionLayout

data class HomeSectionsPageUi(
    val sections: List<HomeSectionUi>,
    val paging: HomePagingUi
)

data class HomePagingUi(
    val currentPage: Int,
    val nextPage: String?,
    val totalPages: Int
) {
    val canLoadMore: Boolean get() = !nextPage.isNullOrBlank()
}

data class HomeSectionUi(
    val id: String,
    val title: String,
    val layout: SectionLayout,
    val order: Int,
    val items: List<HomeCardUi>
)

sealed interface HomeCardUi {
    val id: String
    val composeKey: String
}

data class SquareCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val subtitle: String? = null
) : HomeCardUi

data class QueueCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val subtitle: String? = null,
    val description: String? = null
) : HomeCardUi

data class GridCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val subtitle: String? = null
) : HomeCardUi

data class BigSquareCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val subtitle: String? = null,
) : HomeCardUi