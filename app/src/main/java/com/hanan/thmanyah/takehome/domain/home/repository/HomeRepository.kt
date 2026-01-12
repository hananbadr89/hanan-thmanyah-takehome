package com.hanan.thmanyah.takehome.domain.home.repository

import com.hanan.thmanyah.takehome.domain.home.model.RefreshPolicy
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun observeHomeSections(policy: RefreshPolicy): Flow<SectionsPage>
    suspend fun refreshHomeSections()
    suspend fun loadNextPage(): Boolean
}