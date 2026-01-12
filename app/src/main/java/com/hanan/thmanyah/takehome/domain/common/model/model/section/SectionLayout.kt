package com.hanan.thmanyah.takehome.domain.common.model.model.section

enum class SectionLayout {
    SQUARE,
    QUEUE,
    TWO_LINES_GRID,
    BIG_SQUARE,
    UNKNOWN
}

fun String.toSectionLayout(): SectionLayout =
    when (this.lowercase().trim()) {
        "square" -> SectionLayout.SQUARE
        "queue" -> SectionLayout.QUEUE
        "2_lines_grid",
        "two_lines_grid" -> SectionLayout.TWO_LINES_GRID
        "big_square",
        "big square" -> SectionLayout.BIG_SQUARE

        else -> SectionLayout.UNKNOWN
    }