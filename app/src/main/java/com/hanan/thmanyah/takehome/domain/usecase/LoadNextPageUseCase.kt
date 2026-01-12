package com.hanan.thmanyah.takehome.domain.usecase

import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import javax.inject.Inject

class LoadNextPageUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.loadNextPage()
    }
}