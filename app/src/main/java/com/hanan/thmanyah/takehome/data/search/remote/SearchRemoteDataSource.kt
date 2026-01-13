package com.hanan.thmanyah.takehome.data.search.remote

import com.hanan.thmanyah.takehome.data.common.remote.dto.SectionsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDataSource {

    @GET("search")
    suspend fun search(
        @Query("query") query: String? = null
    ): SectionsResponseDto
}