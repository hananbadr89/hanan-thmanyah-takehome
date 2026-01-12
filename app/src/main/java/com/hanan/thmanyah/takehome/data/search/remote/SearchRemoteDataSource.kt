package com.hanan.thmanyah.takehome.data.search.remote

import com.hanan.thmanyah.takehome.data.common.remote.dto.SectionsResponseDto
import retrofit2.http.GET

interface SearchRemoteDataSource {

    @GET("search")
    suspend fun search(): SectionsResponseDto

}