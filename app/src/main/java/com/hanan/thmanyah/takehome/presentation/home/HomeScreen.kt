package com.hanan.thmanyah.takehome.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hanan.thmanyah.takehome.presentation.home.components.ErrorState
import com.hanan.thmanyah.takehome.presentation.home.components.HomeContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    onLoadMore: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Home") }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
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
                    HomeContent(
                        sections = state.page.sections,
                        canLoadMore = state.page.paging.canLoadMore && !state.isLoadingMore,
                        onLoadMore = onLoadMore
                    )
                }
            }
        }
    }
}