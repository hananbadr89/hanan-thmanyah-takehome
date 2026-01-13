package com.hanan.thmanyah.takehome.presentation.search

import com.hanan.thmanyah.takehome.presentation.model.section.SectionsPageUi

sealed interface SearchUiState {
    data object Idle : SearchUiState
    data object Loading : SearchUiState
    data class Content(val page: SectionsPageUi) : SearchUiState
    data class Error(val message: String) : SearchUiState
}