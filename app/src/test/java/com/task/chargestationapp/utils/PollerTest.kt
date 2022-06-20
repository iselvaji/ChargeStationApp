package com.task.chargestationapp.utils

import app.cash.turbine.test
import com.task.chargestationapp.util.poller.Poller
import com.task.chargestationapp.util.poller.PollerState
import com.task.chargestationapp.util.poller.new
import com.task.chargestationapp.util.poller.strategy.IndefiniteStrategy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PollerTest {

    private fun TestScope.createPollerWithPollInterval(): Poller<Int> = Poller.new(
        coroutineScope = this,
        pollInterval = 1_000,
        pollStrategy = IndefiniteStrategy()
    )

    @Test
    fun `poll and verify that it can still be polled in between`() = runTest {
        val poller = createPollerWithPollInterval()
        var count = 1
        poller.poll { count++ }
        advanceTimeBy(2_000)
        assert(poller.canPoll())
        poller.stop()
    }

    @Test
    fun `wait for poll to complete & verify poll state`() = runTest {
        val poller = createPollerWithPollInterval()
        var count = 1
        poller.poll { count++ }
        advanceTimeBy(4_000)
        poller.pollerStateFlow.test {
            assert(expectMostRecentItem() == PollerState.InProgress(4))
        }
        poller.stop()
    }

}