package com.hanan.thmanyah.takehome.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.components.ErrorState
import com.hanan.thmanyah.takehome.presentation.home.components.HomeContent
import com.hanan.thmanyah.takehome.presentation.search.components.NoResults
import com.hanan.thmanyah.takehome.presentation.search.components.SearchEmptyHint
import com.hanan.thmanyah.takehome.presentation.search.components.SearchField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    query: String,
    state: SearchUiState,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SearchField(
                value = query,
                onValueChange = onQueryChange,
                onClear = onClear,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            when (state) {
                SearchUiState.Idle -> {
                    SearchEmptyHint(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                SearchUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is SearchUiState.Error -> {
                    ErrorState(
                        message = state.message,
                        onRetry = { onQueryChange(query) },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is SearchUiState.Content -> {
                    if (state.page.sections.isEmpty()) {
                        NoResults(
                            query = query,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        HomeContent(
                            sections = state.page.sections,
                            modifier = Modifier.fillMaxSize(),
                            onLoadMore = {},
                            canLoadMore = false
                        )
                    }
                }
            }
        }
    }
}