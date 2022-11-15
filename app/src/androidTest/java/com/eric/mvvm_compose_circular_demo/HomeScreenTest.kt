package com.eric.mvvm_compose_circular_demo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.eric.mvvm_compose_circular_demo.ui.feature.home.HomeActivity

import org.junit.Test
import org.junit.Rule

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    @Test
    fun `ShowBooking`() {
        val button = composeTestRule.onNode(hasTestTag("RefreshButton"))
        val milesLeftText = composeTestRule.onNode(hasTestTag("milesTag"))
        val energyLeftText = composeTestRule.onNode(hasTestTag("energyTag"))

        button.assertIsDisplayed()
        milesLeftText.assertDoesNotExist()
        energyLeftText.assertDoesNotExist()
        button.performClick()
        Thread.sleep(3000) //ideally use idle registry!!
        milesLeftText.assertIsDisplayed()
        energyLeftText.assertIsDisplayed()
    }
}