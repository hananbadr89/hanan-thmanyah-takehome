package com.hanan.thmanyah.takehome.data.repository

import com.hanan.thmanyah.takehome.data.mapper.toDomain
import com.hanan.thmanyah.takehome.data.remote.ThmanyahHomeApi
import com.hanan.thmanyah.takehome.domain.model.HomeSectionsPage
import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: ThmanyahHomeApi
) : HomeRepository {
    override suspend fun getHomeSections(): HomeSectionsPage {
        return api.getHomeSections().toDomain()
    }
}