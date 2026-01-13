package com.hanan.thmanyah.takehome.presentation.home

import com.hanan.thmanyah.takehome.presentation.model.section.SectionsPageUi

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Content(
        val page: SectionsPageUi,
        val isRefreshing: Boolean = false,
        val isLoadingMore: Boolean = false,
        val errorMessage: String? = null
    ) : HomeUiState

    data class Error(val message: String) : HomeUiState
}