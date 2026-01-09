package com.hanan.thmanyah.takehome.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanan.thmanyah.takehome.domain.usecase.GetHomeSectionsUseCase
import com.hanan.thmanyah.takehome.presentation.home.mapper.toHomeSectionUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSections: GetHomeSectionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state = _state.asStateFlow()

    init {
        loadHome()
    }

    private fun loadHome() {
        viewModelScope.launch {
            runCatching {
                getHomeSections()
            }.onSuccess { page ->
                _state.value = HomeUiState.Content(
                    sections = page.sections.toHomeSectionUi()
                )
            }.onFailure { throwable ->
                _state.value = HomeUiState.Error(
                    message = throwable.message ?: "Something went wrong"
                )
            }
        }
    }
}