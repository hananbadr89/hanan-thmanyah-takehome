package com.hanan.thmanyah.takehome.data.home.remote

import com.hanan.thmanyah.takehome.data.common.remote.dto.SectionsResponseDto
import retrofit2.http.GET
import retrofit2.http.Url

interface HomeRemoteDataSource {

    @GET("home_sections")
    suspend fun getHomeSections(
    ): SectionsResponseDto

    @GET
    suspend fun getHomeSectionsByPath(
        @Url nextPageUrl: String
    ): SectionsResponseDto

}