package com.hanan.thmanyah.takehome.presentation.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.rules.ActivityScenarioRule

class HomeRobot(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
) {

    fun setContent(
        state: HomeUiState,
        onLoadMore: () -> Unit = {}
    ): HomeRobot {
        rule.setContent {
            HomeScreen(
                state = state,
                onLoadMore = onLoadMore
            )
        }
        return this
    }

    fun assertLoading(): HomeRobot {
        rule.onNodeWithTag(HomeTestTags.LOADING).assertIsDisplayed()
        return this
    }

    fun assertListVisible(): HomeRobot {
        rule.onNodeWithTag(HomeTestTags.LIST).assertIsDisplayed()
        return this
    }

    fun assertErrorVisible(): HomeRobot {
        rule.onNodeWithTag(HomeTestTags.ERROR).assertIsDisplayed()
        return this
    }

    fun assertErrorMessage(message: String): HomeRobot {
        rule.onNodeWithText(message).assertIsDisplayed()
        return this
    }

    fun assertSectionTitleVisible(title: String): HomeRobot {
        rule.onNodeWithText(title).performScrollTo().assertIsDisplayed()
        return this
    }

    fun assertCardTitleVisible(title: String): HomeRobot {
        rule.onNodeWithText(title).performScrollTo().assertIsDisplayed()
        return this
    }

    fun scrollListToIndex(index: Int): HomeRobot {
        rule.onNodeWithTag(HomeTestTags.LIST).performScrollToIndex(index)
        return this
    }
}