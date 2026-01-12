package com.hanan.thmanyah.takehome.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanan.thmanyah.takehome.domain.usecase.GetHomeSectionsUseCase
import com.hanan.thmanyah.takehome.domain.usecase.LoadNextPageUseCase
import com.hanan.thmanyah.takehome.presentation.home.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSections: GetHomeSectionsUseCase,
    private val loadNextPageUseCase: LoadNextPageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state = _state

    private var loadMoreJob: Job? = null

    init {
        loadHome()
    }

    private fun loadHome() {
        viewModelScope.launch {
            getHomeSections()
                .onStart {
                    _state.value = HomeUiState.Loading
                }
                .map { it.toUi() }
                .catch { e ->
                    _state.value = HomeUiState.Error(e.message ?: "Something went wrong")
                }
                .collect { pageUi ->
                    val current = _state.value
                    _state.value = when (current) {
                        is HomeUiState.Content -> current.copy(
                            page = pageUi,
                            isRefreshing = false,
                            isLoadingMore = false,
                            errorMessage = null
                        )

                        else -> HomeUiState.Content(page = pageUi)
                    }
                }
        }
    }

    fun loadNextPage() {
        if (loadMoreJob?.isActive == true) return

        loadMoreJob = viewModelScope.launch {
            loadNextPageUseCase()
        }
    }
}