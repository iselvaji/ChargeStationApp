package com.task.chargestationapp.util.poller

import kotlinx.coroutines.CancellationException

/**
 * Exception used when [Poller] is stopped
 */
sealed class PollerException : CancellationException() {
    object ContinuePoll : PollerException()
    object StopPoll : PollerException()
}
