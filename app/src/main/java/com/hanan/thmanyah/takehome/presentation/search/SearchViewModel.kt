package com.hanan.thmanyah.takehome.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.search.usecase.SearchUseCase
import com.hanan.thmanyah.takehome.presentation.home.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _state = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val state: StateFlow<SearchUiState> = _state

    val query: StateFlow<String> = queryFlow

    init {
        observeQuery()
    }

    fun onQueryChanged(value: String) {
        queryFlow.value = value
    }

    fun clearQuery() {
        queryFlow.value = ""
        _state.value = SearchUiState.Idle
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun observeQuery() {
        queryFlow
            .map { it.trim() }
            .debounce(200)
            .distinctUntilChanged()
            .onEach { query ->
                if (query.isBlank()) {
                    _state.value = SearchUiState.Idle
                }
            }
            .filter { it.isNotBlank() }
            .flatMapLatest { query ->
                flow {
                    _state.value = SearchUiState.Loading
                    emit(searchUseCase(query))
                }.catch { e ->
                    _state.value = SearchUiState.Error(e.message.orEmpty())
                    emit(SectionsPage(sections = emptyList()))
                }
            }
            .onEach { sections ->
                _state.value = SearchUiState.Content(sections.toUi())
            }.launchIn(viewModelScope)
    }
}