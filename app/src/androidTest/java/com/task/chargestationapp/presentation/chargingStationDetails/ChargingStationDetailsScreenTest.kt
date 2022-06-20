package com.task.chargestationapp.presentation.chargingStationDetails

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.SavedStateHandle
import com.task.chargestationapp.MainActivity
import com.task.chargestationapp.di.AppModule
import com.task.chargestationapp.presentation.chargingstationsdetails.ChargingStationDetailViewModel
import com.task.chargestationapp.presentation.chargingstationsdetails.ChargingStationDetailsScreen
import com.task.chargestationapp.util.Constants
import com.task.chargestationapp.util.TestData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class ChargingStationDetailsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
         hiltRule.inject()
    }

    @Test
    fun test_charging_details_display_success() {

        val testData = TestData.chargingStation
        val state = SavedStateHandle()
        state[Constants.SELECTED_STATION] = testData

        composeRule.setContent {
            ChargingStationDetailsScreen(
                testData, ChargingStationDetailViewModel(state)
            )
        }

        testData.title?.let {
            composeRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

}