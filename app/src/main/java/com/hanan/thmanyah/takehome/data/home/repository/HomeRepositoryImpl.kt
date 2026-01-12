package com.hanan.thmanyah.takehome.data.home.repository

import com.hanan.thmanyah.takehome.data.home.mapper.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.data.home.local.HomeLocalDataSource
import com.hanan.thmanyah.takehome.data.home.mapper.dto.sectionKey
import com.hanan.thmanyah.takehome.data.home.mapper.dto.toDomainPage
import com.hanan.thmanyah.takehome.data.home.remote.HomeRemoteDataSource
import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionDto
import com.hanan.thmanyah.takehome.di.IoDispatcher
import com.hanan.thmanyah.takehome.domain.home.model.RefreshPolicy
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.hours

class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource,
    private val local: HomeLocalDataSource,
    private val decoder: ContentDecoder,
    @IoDispatcher private val io: CoroutineDispatcher,
) : HomeRepository {

    private val cacheTtlMillis: Long = 2.hours.inWholeMilliseconds

    private val refreshSignals = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun observeHomeSections(policy: RefreshPolicy): Flow<SectionsPage> = channelFlow {
        val jobDb = launch(io) {
            local.observeCached()
                .filterNotNull()
                .map { cached -> cached.dto.toDomainPage(decoder) }
                .collectLatest { page -> send(page) }
        }

        val jobRefresh = launch(io) {
            when (policy) {
                RefreshPolicy.CACHE_ONLY -> {

                }

                RefreshPolicy.FORCE_REFRESH -> {
                    runCatching { refreshHomeSectionsInternal() }
                }

                RefreshPolicy.CACHE_FIRST -> {
                    val cached = local.getCached()
                    val now = System.currentTimeMillis()

                    val isStaleOrEmpty =
                        cached == null || (now - cached.paging.updatedAt) >= cacheTtlMillis

                    if (isStaleOrEmpty) {
                        runCatching { refreshHomeSectionsInternal() }
                    }
                }
            }

            refreshSignals.collect {
                runCatching { refreshHomeSectionsInternal() }
            }
        }

        awaitClose {
            jobDb.cancel()
            jobRefresh.cancel()
        }
    }

    override suspend fun refreshHomeSections() {
        refreshSignals.tryEmit(Unit)
    }

    override suspend fun loadNextPage(): Boolean = withContext(io) {
        val cached = local.getCached() ?: return@withContext false
        val paging = cached.paging
        val next = paging.nextPage?.takeIf { it.isNotBlank() } ?: return@withContext false

        val nextDto = remote.getHomeSectionsByPath(next)

        val mergedSections = mergeSections(
            old = cached.dto.sections.orEmpty(),
            new = nextDto.sections.orEmpty()
        )

        val aggregated = cached.dto.copy(
            sections = mergedSections,
            pagination = nextDto.pagination
        )

        val newPaging = paging.copy(
            nextPage = nextDto.pagination?.nextPage,
            totalPages = nextDto.pagination?.totalPages ?: paging.totalPages,
            currentPage = paging.currentPage + 1,
            updatedAt = System.currentTimeMillis()
        )

        local.saveAggregated(aggregatedDto = aggregated, paging = newPaging)
        true
    }

    private fun mergeSections(
        old: List<SectionDto>,
        new: List<SectionDto>
    ): List<SectionDto> {
        val map = LinkedHashMap<String, SectionDto>()
        (old + new).forEach { section ->
            map.putIfAbsent(section.sectionKey(), section)
        }
        return map.values.toList()
    }

    private suspend fun refreshHomeSectionsInternal() = withContext(io) {
        val dto = remote.getHomeSections()
        local.save(dto)
    }
}