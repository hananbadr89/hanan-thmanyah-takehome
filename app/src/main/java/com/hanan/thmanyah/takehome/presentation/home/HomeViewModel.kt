package com.hanan.thmanyah.takehome.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanan.thmanyah.takehome.domain.usecase.GetHomeSectionsUseCase
import com.hanan.thmanyah.takehome.presentation.home.mapper.toUiOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSections: GetHomeSectionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state = _state

    init {
        loadHome()
    }

    private fun loadHome() {
        viewModelScope.launch {
            getHomeSections()
                .onStart {
                    _state.value = HomeUiState.Loading
                }
                .catch { e ->
                    _state.value = HomeUiState.Error(e.message ?: "Something went wrong")
                }
                .collect { page ->
                    _state.value = HomeUiState.Content(
                        sections = page.sections.mapNotNull { it.toUiOrNull() }
                    )
                }
        }
    }
}