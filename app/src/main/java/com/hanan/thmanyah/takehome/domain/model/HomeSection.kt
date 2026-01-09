package com.hanan.thmanyah.takehome.domain.model

enum class ContentType {
    PODCAST,
    UNKNOWN
}

data class HomeSectionsPage(
    val sections: List<HomeSection>
)

data class HomeSection(
    val name: String,
    val type: String,
    val contentType: ContentType,
    val order: Int,
    val items: List<HomeItem>
)

sealed interface HomeItem

data class PodcastItem(
    val id: String,
    val name: String,
    val description: String?,
    val avatarUrl: String?
) : HomeItem