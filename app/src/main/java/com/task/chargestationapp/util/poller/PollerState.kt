package com.task.chargestationapp.util.poller

import kotlinx.coroutines.CancellationException

/**
 * Used to hold the state of [Poller]
 */
sealed class PollerState<out T> {
    object Initial : PollerState<Nothing>()
    data class InProgress<S>(val result: S) : PollerState<S>()
    object Complete : PollerState<Nothing>()
    class Cancelled(val exception: CancellationException) : PollerState<Nothing>()
}
