package com.hanan.thmanyah.takehome.domain.repository

import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage

interface HomeRepository {
    suspend fun getHomeSections(): SectionsPage
}