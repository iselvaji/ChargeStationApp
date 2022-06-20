package com.task.chargestationapp.presentation.chargingStations

import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.ramcosta.composedestinations.DestinationsNavHost
import com.task.chargestationapp.MainActivity
import com.task.chargestationapp.R
import com.task.chargestationapp.di.AppModule
import com.task.chargestationapp.presentation.NavGraphs
import com.task.chargestationapp.ui.theme.ChargeStationAppTheme
import com.task.chargestationapp.util.Constants.CONTENT_DESC_MAP
import com.task.chargestationapp.util.TestUtil.disableNetwork
import com.task.chargestationapp.util.TestUtil.enableNetwork
import com.task.chargestationapp.util.TestUtil.waitUntilDoesNotExist
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(AppModule::class)
class ChargingStationsMapScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            ChargeStationAppTheme{
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

    @Test
    fun test_isMapVisible() {

        val progressMsg = composeRule.activity.getString(R.string.updating)
        composeRule.onNodeWithText(progressMsg).assertIsDisplayed()
        composeRule.waitUntilDoesNotExist(hasText(progressMsg))
        onView(withContentDescription(CONTENT_DESC_MAP)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isChargingStationMarkersLoaded() {

        val progressMsg = composeRule.activity.getString(R.string.updating)
        composeRule.onNodeWithText(progressMsg).assertIsDisplayed()
        composeRule.waitUntilDoesNotExist(hasText(progressMsg))
        onView(withContentDescription(CONTENT_DESC_MAP)).check(matches(isDisplayed()))

        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains(CONTENT_DESC_MAP).childSelector(UiSelector().instance(1)))
        assertEquals(true, marker.exists() && marker.isEnabled)
    }

    @Test
    fun test_isNetworkErrorVisible() {

        disableNetwork()

        val progressMsg = composeRule.activity.getString(R.string.updating)
        composeRule.waitUntilDoesNotExist(hasText(progressMsg))

        val message = composeRule.activity.getString(R.string.err_connectivity)
        composeRule.onNodeWithText(message).assertIsDisplayed()

        enableNetwork()
    }
}
