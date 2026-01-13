package com.hanan.thmanyah.takehome.presentation.home

import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.home.usecase.GetHomeSectionsUseCase
import com.hanan.thmanyah.takehome.domain.home.usecase.LoadNextPageUseCase
import com.hanan.thmanyah.takehome.presentation.MainDispatcherRule
import com.hanan.thmanyah.takehome.presentation.mapper.toUi
import com.hanan.thmanyah.takehome.presentation.model.section.SectionsPageUi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getHomeSections: GetHomeSectionsUseCase = mockk()
    private val loadNextPageUseCase: LoadNextPageUseCase = mockk(relaxed = true)

    @Test
    fun `init emits Loading then Content when first page arrives`() = runTest {
        val upstream = MutableSharedFlow<SectionsPage>(replay = 0)

        coEvery { getHomeSections.invoke(any()) } returns upstream

        val vm = HomeViewModel(getHomeSections, loadNextPageUseCase)
        runCurrent()

        assertTrue(vm.state.value is HomeUiState.Loading)

        val domainPage = SectionsPage(
            sections = emptyList()
        )

        upstream.emit(domainPage)
        runCurrent()

        assertTrue(vm.state.value is HomeUiState.Content)
        coVerify(exactly = 1) { getHomeSections.invoke(any()) }
    }

    @Test
    fun `when upstream throws, emits Error with message`() = runTest {
        val errorMsg = "boom"
        coEvery { getHomeSections.invoke() } returns kotlinx.coroutines.flow.flow {
            throw IllegalStateException(errorMsg)
        }

        val vm = HomeViewModel(getHomeSections, loadNextPageUseCase)
        advanceUntilIdle()

        assertTrue(vm.state.value is HomeUiState.Error)
        val err = vm.state.value as HomeUiState.Error
        assertEquals(errorMsg, err.message)
    }

    @Test
    fun `loadNextPage does not start another job while one is active`() = runTest {
        coEvery { getHomeSections.invoke(any()) } returns MutableSharedFlow(replay = 0)

        val gate = CompletableDeferred<Unit>()
        coEvery { loadNextPageUseCase.invoke() } coAnswers {
            gate.await()
            true
        }
        val vm = HomeViewModel(getHomeSections, loadNextPageUseCase)
        runCurrent()

        vm.loadNextPage()
        runCurrent()

        vm.loadNextPage()
        vm.loadNextPage()

        coVerify(exactly = 1) { loadNextPageUseCase.invoke() }

        gate.complete(Unit)
        runCurrent()

        vm.loadNextPage()
        runCurrent()

        coVerify(exactly = 2) { loadNextPageUseCase.invoke() }
    }

    @Test
    fun `new emission while in Content resets flags and clears errorMessage`() = runTest {
        val upstream = MutableSharedFlow<SectionsPage>(replay = 0)
        coEvery { getHomeSections.invoke(any()) } returns upstream

        mockkStatic("com.hanan.thmanyah.takehome.presentation.mapper.HomeUiMapperKt")

        val firstDomainPage = SectionsPage(sections = emptyList())
        val secondDomainPage = SectionsPage(sections = emptyList())

        val firstUiPage = mockk<SectionsPageUi>(relaxed = true)
        val secondUiPage = mockk<SectionsPageUi>(relaxed = true)

        coEvery { firstDomainPage.toUi() } returns firstUiPage
        coEvery { secondDomainPage.toUi() } returns secondUiPage

        val vm = HomeViewModel(getHomeSections, loadNextPageUseCase)
        runCurrent()

        upstream.emit(firstDomainPage)
        runCurrent()
        assertTrue(vm.state.value is HomeUiState.Content)

        val stateField =
            HomeViewModel::class.java.getDeclaredField("_state").apply { isAccessible = true }

        @Suppress("UNCHECKED_CAST")
        val internalState =
            stateField.get(vm) as kotlinx.coroutines.flow.MutableStateFlow<HomeUiState>

        internalState.value = HomeUiState.Content(
            page = firstUiPage,
            isRefreshing = true,
            isLoadingMore = true,
            errorMessage = "old error"
        )

        upstream.emit(secondDomainPage)
        runCurrent()

        val content = vm.state.value as HomeUiState.Content
        assertEquals(secondUiPage, content.page)
        assertEquals(false, content.isRefreshing)
        assertEquals(false, content.isLoadingMore)
        assertEquals(null, content.errorMessage)

        coVerify(exactly = 1) { getHomeSections.invoke(any()) }
    }
}