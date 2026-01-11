package com.hanan.thmanyah.takehome.domain.usecase

import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): SectionsPage = repository.getHomeSections()
}