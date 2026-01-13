package com.hanan.thmanyah.takehome.presentation.home

import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class HomeScreenUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private val robot = HomeRobot(rule)

    @Test
    fun shows_loading_state() {
        val state = mutableStateOf<HomeUiState>(HomeUiState.Loading)

        robot
            .setContent(state.value)
            .assertLoading()
    }

    @Test
    fun shows_error_state_with_message() {
        val state = HomeUiState.Error(message = "Network error")

        robot
            .setContent(state)
            .assertErrorVisible()
            .assertErrorMessage("Network error")
    }

    @Test
    fun shows_content_with_sections_list() {
        val state = HomeUiState.Content(page = HomeTestData.homePage())

        robot
            .setContent(state)
            .assertListVisible()
            .assertSectionTitleVisible("Section 1")
            .assertCardTitleVisible("Card 1")
    }

    @Test
    fun calls_onLoadMore_when_scrolled_near_end_and_canLoadMore_true() {
        val calls = AtomicInteger(0)

        val state = HomeUiState.Content(
            page = HomeTestData.homePage(
                sectionCount = 10,
                canLoadMore = true
            ),
            isLoadingMore = false
        )

        robot.setContent(
            state = state,
            onLoadMore = { calls.incrementAndGet() }
        )
            .assertListVisible()
            .scrollListToIndex(9)

        rule.apply {
            waitForIdle()
            runOnIdle {
                assertTrue("Expected onLoadMore to be called at least once", calls.get() > 0)
            }
        }
    }

    @Test
    fun does_not_call_onLoadMore_when_canLoadMore_false() {
        val calls = AtomicInteger(0)

        val state = HomeUiState.Content(
            page = HomeTestData.homePage(sectionCount = 10, canLoadMore = false),
            isLoadingMore = false
        )

        robot.setContent(
            state = state,
            onLoadMore = { calls.incrementAndGet() }
        )
            .assertListVisible()
            .scrollListToIndex(9)

        rule.waitForIdle()
        rule.runOnIdle {
            assertTrue("Expected onLoadMore NOT to be called", calls.get() == 0)
        }
    }
}