package com.hanan.thmanyah.takehome.presentation.model.card

sealed interface CardUi {
    val id: String
    val composeKey: String
}

data class SquareCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val meta: String? = null
) : CardUi

data class QueueCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val meta1: String? = null,
    val meta2: String? = null
) : CardUi

data class GridCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val meta1: String? = null,
    val meta2: String? = null,
) : CardUi

data class BigSquareCardUi(
    override val id: String,
    override val composeKey: String,
    val imageUrl: String?,
    val title: String,
    val meta: String? = null,
) : CardUi