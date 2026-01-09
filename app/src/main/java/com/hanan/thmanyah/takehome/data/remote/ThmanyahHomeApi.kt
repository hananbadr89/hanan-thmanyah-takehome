package com.hanan.thmanyah.takehome.data.remote

import com.hanan.thmanyah.takehome.data.remote.dto.HomeSectionsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ThmanyahHomeApi {

    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int
    ): HomeSectionsResponseDto
}