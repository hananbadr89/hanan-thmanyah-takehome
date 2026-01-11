package com.hanan.thmanyah.takehome.data.home.remote

import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionsResponseDto
import retrofit2.http.GET

interface HomeRemoteDataSource {

    @GET("home_sections")
    suspend fun getHomeSections(
    ): SectionsResponseDto
}