package com.hanan.thmanyah.takehome.domain.home.model.section

import com.hanan.thmanyah.takehome.domain.home.model.item.HomeItem

data class SectionsPage(
    val sections: List<Section>
)

data class Section(
    val id: String,
    val title: String,
    val layout: SectionLayout,
    val contentType: ContentType,
    val order: Int,
    val items: List<HomeItem>
)