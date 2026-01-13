package com.hanan.thmanyah.takehome.presentation.search

import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionsPageUi

sealed interface SearchUiState {
    data object Idle : SearchUiState
    data object Loading : SearchUiState
    data class Content(val page: HomeSectionsPageUi) : SearchUiState
    data class Error(val message: String) : SearchUiState
}