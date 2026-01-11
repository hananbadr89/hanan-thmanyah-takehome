package com.hanan.thmanyah.takehome.domain.usecase

import com.hanan.thmanyah.takehome.domain.home.model.RefreshPolicy
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(policy: RefreshPolicy =RefreshPolicy.CACHE_FIRST): Flow<SectionsPage> =
        repository.observeHomeSections(policy)
}