package com.task.chargestationapp.util.poller

import com.task.chargestationapp.util.poller.strategy.PollStrategy
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal class PollerImpl<T>(
    private val coroutineScope: CoroutineScope,
    private val pollInterval: Long,
    private val pollStrategy: PollStrategy
) : Poller<T> {

    private val _pollerStateFlow: MutableStateFlow<PollerState<T>> = MutableStateFlow(PollerState.Initial)

    override val pollerStateFlow: StateFlow<PollerState<T>>
        get() = _pollerStateFlow.asStateFlow()

    // holds the job of running poll
    private var pollerJob: Job? = null

    // a flag whether the poll is active or not
    private var isPolling: Boolean = false

    // elapsed time since poll started
    private var elapsedPollTime: Long = 0

    override fun poll(pollBlock: suspend () -> T): Job {
        // update the local flags
        isPolling = true
        elapsedPollTime = 0

        // start the poll job
        return coroutineScope.launch {
            // iterate until
            // - the coroutineScope is active or
            // - the poll is not completed
            while (isActive && canPoll()) {
                // execute to pollBlock and update the flow
                val pollResult = pollBlock()
                _pollerStateFlow.value = PollerState.InProgress(pollResult)
                // update the elapsed time
                elapsedPollTime += pollInterval
                // suspend until next poll
                delay(pollInterval)
            }

            // if execution reaches here then poll is complete
            _pollerStateFlow.value = PollerState.Complete
        }.also { job ->
            // update the flow when the poll job is finished
            // and stop polling
            job.invokeOnCompletion { throwable ->
                _pollerStateFlow.value = when (throwable) {
                    is CancellationException -> PollerState.Cancelled(throwable)
                    else -> PollerState.Complete
                }
                stop(PollerException.StopPoll)
            }
            pollerJob = job
        }
    }

    override fun canPoll(): Boolean = isPolling && pollStrategy.canPoll(pollInterval, elapsedPollTime)

    override fun isPolling(): Boolean = isPolling

    override fun stop(cause: PollerException) {
        isPolling = false
        pollerJob?.cancel(cause)
        pollerJob = null
    }
}
