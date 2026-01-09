package com.hanan.thmanyah.takehome.presentation.home

import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Content(
        val sections: List<HomeSectionUi>
    ) : HomeUiState

    data class Error(
        val message: String
    ) : HomeUiState
}