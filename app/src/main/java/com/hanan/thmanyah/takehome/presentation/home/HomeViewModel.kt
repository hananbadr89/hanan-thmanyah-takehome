package com.hanan.thmanyah.takehome.presentation.home

import androidx.lifecycle.ViewModel
import com.hanan.thmanyah.takehome.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSectionsUseCase: GetHomeSectionsUseCase
) : ViewModel() {

}