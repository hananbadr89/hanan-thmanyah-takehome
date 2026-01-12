package com.hanan.thmanyah.takehome.domain.search.repository

import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage

interface SearchRepository {
    suspend fun search(): SectionsPage
}