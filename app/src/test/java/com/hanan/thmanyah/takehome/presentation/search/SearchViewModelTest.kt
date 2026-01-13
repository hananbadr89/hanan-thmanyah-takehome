package com.hanan.thmanyah.takehome.presentation.search

import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.search.usecase.SearchUseCase
import com.hanan.thmanyah.takehome.presentation.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val searchUseCase: SearchUseCase = mockk()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        viewModel = SearchViewModel(searchUseCase)
    }

    @Test
    fun `given blank query when changed then stays idle and does not call use case`() = runTest {
        coEvery { searchUseCase(any()) } returns SectionsPage(emptyList())

        viewModel.onQueryChanged("   ")
        advanceTimeBy(250)
        runCurrent()

        coVerify(exactly = 0) { searchUseCase(any()) }
        assertTrue(viewModel.state.value is SearchUiState.Idle)
    }

    @Test
    fun `given query with spaces when changed then trims before calling use case`() = runTest {
        coEvery { searchUseCase("pod") } returns SectionsPage(emptyList())

        viewModel.onQueryChanged("   pod   ")
        advanceTimeBy(200)
        runCurrent()
        advanceUntilIdle()

        coVerify(exactly = 1) { searchUseCase("pod") }
    }

    @Test
    fun `given user types quickly when debounced then only last query triggers search`() = runTest {
        coEvery { searchUseCase(any()) } returns SectionsPage(emptyList())

        viewModel.onQueryChanged("p")
        viewModel.onQueryChanged("po")
        viewModel.onQueryChanged("pod")

        advanceTimeBy(199)
        runCurrent()
        coVerify(exactly = 0) { searchUseCase(any()) }

        advanceTimeBy(1)
        runCurrent()
        advanceUntilIdle()

        coVerify(exactly = 1) { searchUseCase("pod") }
        coVerify(exactly = 0) { searchUseCase("p") }
        coVerify(exactly = 0) { searchUseCase("po") }
    }

    @Test
    fun `given same query twice when changed then does not trigger second request`() = runTest {
        coEvery { searchUseCase("pod") } returns SectionsPage(emptyList())

        viewModel.onQueryChanged("pod")
        advanceTimeBy(200)
        runCurrent()
        advanceUntilIdle()

        viewModel.onQueryChanged("pod")
        advanceTimeBy(200)
        runCurrent()
        advanceUntilIdle()

        coVerify(exactly = 1) { searchUseCase("pod") }
    }

    @Test
    fun `given in flight search when new query arrives then latest result wins`() = runTest {
        val first = CompletableDeferred<SectionsPage>()
        coEvery { searchUseCase("a") } coAnswers { first.await() }
        coEvery { searchUseCase("ab") } returns SectionsPage(emptyList())

        viewModel.onQueryChanged("a")
        advanceTimeBy(200)
        runCurrent()

        viewModel.onQueryChanged("ab")
        advanceTimeBy(200)
        runCurrent()
        advanceUntilIdle()

        coVerify(exactly = 1) { searchUseCase("a") }
        coVerify(exactly = 1) { searchUseCase("ab") }

        first.complete(SectionsPage(sections = listOf()))
        advanceUntilIdle()

        assertTrue(viewModel.state.value is SearchUiState.Content)
    }

    @Test
    fun `given slow use case when search starts then emits loading then content`() = runTest {
        val deferred = CompletableDeferred<SectionsPage>()
        coEvery { searchUseCase("pod") } coAnswers { deferred.await() }

        viewModel.onQueryChanged("pod")
        advanceTimeBy(200)
        runCurrent()

        assertTrue(viewModel.state.value is SearchUiState.Loading)

        deferred.complete(SectionsPage(emptyList()))
        advanceUntilIdle()

        assertTrue(viewModel.state.value is SearchUiState.Content)
    }

    @Test
    fun `usecase throws when searching then emits Error`() = runTest {
        coEvery { searchUseCase("pod") } throws RuntimeException("boom")

        viewModel.onQueryChanged("pod")
        advanceTimeBy(200)
        runCurrent()
        advanceUntilIdle()

        assertTrue(viewModel.state.value is SearchUiState.Error)
    }

    @Test
    fun `clearQuery resets query and sets Idle`() = runTest {
        viewModel.onQueryChanged("pod")

        viewModel.clearQuery()
        runCurrent()

        assertTrue(viewModel.query.value.isEmpty())
        assertTrue(viewModel.state.value is SearchUiState.Idle)
    }
}