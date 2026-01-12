package com.hanan.thmanyah.takehome.domain.home.usecase

import com.hanan.thmanyah.takehome.domain.home.model.RefreshPolicy
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(policy: RefreshPolicy = RefreshPolicy.CACHE_FIRST): Flow<SectionsPage> =
        repository.observeHomeSections(policy)
}