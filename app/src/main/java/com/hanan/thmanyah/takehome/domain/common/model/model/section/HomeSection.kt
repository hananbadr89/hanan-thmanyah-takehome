package com.hanan.thmanyah.takehome.domain.common.model.model.section

import com.hanan.thmanyah.takehome.data.home.local.model.PagingState
import com.hanan.thmanyah.takehome.domain.common.model.model.item.HomeItem

data class SectionsPage(
    val sections: List<Section>,
    val paging: PagingState
)

data class Section(
    val id: String,
    val title: String,
    val layout: SectionLayout,
    val contentType: ContentType,
    val order: Int,
    val items: List<HomeItem>
)