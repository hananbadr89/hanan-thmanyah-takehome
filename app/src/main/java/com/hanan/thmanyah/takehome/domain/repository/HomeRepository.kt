package com.hanan.thmanyah.takehome.domain.repository

import com.hanan.thmanyah.takehome.domain.model.HomeSectionsPage

interface HomeRepository {
    suspend fun getHomeSections(): HomeSectionsPage
}