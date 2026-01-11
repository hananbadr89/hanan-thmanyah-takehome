package com.hanan.thmanyah.takehome.data.home.repository

import com.hanan.thmanyah.takehome.data.home.mapper.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.data.home.local.HomeLocalDataSource
import com.hanan.thmanyah.takehome.data.home.mapper.dto.toDomainPage
import com.hanan.thmanyah.takehome.data.home.remote.HomeRemoteDataSource
import com.hanan.thmanyah.takehome.di.IoDispatcher
import com.hanan.thmanyah.takehome.domain.home.exception.NoCacheException
import com.hanan.thmanyah.takehome.domain.home.model.RefreshPolicy
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource,
    private val local: HomeLocalDataSource,
    private val decoder: ContentDecoder,
    @IoDispatcher private val io: CoroutineDispatcher,
) : HomeRepository {

    private val cacheTtlMillis = 2 * 60 * 60 * 1000L // 2 hours

    override suspend fun getHomeSections(
        policy: RefreshPolicy
    ): SectionsPage = withContext(io) {
        val now = System.currentTimeMillis()
        val cached = local.getCached()

        val isCacheValid = cached?.let { (now - it.updatedAt) < cacheTtlMillis } == true

        when (policy) {
            RefreshPolicy.CACHE_ONLY -> {
                val dto = cached?.dto ?: throw NoCacheException()
                return@withContext dto.toDomainPage(decoder)
            }

            RefreshPolicy.FORCE_REFRESH -> {
                return@withContext fetchAndCache()
            }

            RefreshPolicy.CACHE_FIRST -> {
                if (isCacheValid) {
                    return@withContext cached.dto.toDomainPage(decoder)
                }

                return@withContext runCatching { fetchAndCache() }
                    .getOrElse { throwable ->
                        val staleDto = cached?.dto
                        if (staleDto != null) staleDto.toDomainPage(decoder)
                        else throw throwable
                    }
            }
        }
    }

    private suspend fun fetchAndCache(): SectionsPage {
        val dto = remote.getHomeSections()
        local.save(dto)
        return dto.toDomainPage(decoder)
    }
}