package com.hanan.thmanyah.takehome.presentation.home.model

import com.hanan.thmanyah.takehome.domain.model.SectionLayout

data class HomeSectionUi(
    val title: String,
    val layout: SectionLayout,
    val items: List<PodcastUi>
)

data class PodcastUi(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val episodes: String?,
    val duration: String?
)