package com.hanan.thmanyah.takehome.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hanan.thmanyah.takehome.presentation.ui.components.error.ErrorState
import com.hanan.thmanyah.takehome.presentation.ui.components.sections.SectionsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onLoadMore: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (state) {
            HomeUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is HomeUiState.Error -> {
                ErrorState(
                    message = state.message,
                    onRetry = {},
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is HomeUiState.Content -> {
                SectionsContent(
                    sections = state.page.sections,
                    canLoadMore = state.page.paging?.canLoadMore == true && !state.isLoadingMore,
                    onLoadMore = onLoadMore
                )
            }
        }
    }
}