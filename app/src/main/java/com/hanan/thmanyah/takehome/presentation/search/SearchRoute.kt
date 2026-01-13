package com.hanan.thmanyah.takehome.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val query by viewModel.query.collectAsState()

    SearchScreen(
        modifier = modifier,
        query = query,
        state = state,
        onQueryChange = viewModel::onQueryChanged,
        onClear = viewModel::clearQuery
    )
}