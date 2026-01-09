package com.hanan.thmanyah.takehome.data.remote

import com.hanan.thmanyah.takehome.data.remote.dto.HomeSectionsResponseDto
import retrofit2.http.GET

interface ThmanyahHomeApi {

    @GET("home_sections")
    suspend fun getHomeSections(
    ): HomeSectionsResponseDto
}