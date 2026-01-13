package com.hanan.thmanyah.takehome.domain.search.usecase

import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): SectionsPage = repository.search(query)
}