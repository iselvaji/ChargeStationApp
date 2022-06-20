package com.task.chargestationapp.presentation.chargingstations

sealed class ChargingStationsEvent {
    object PollData: ChargingStationsEvent()
    object StopPollingData: ChargingStationsEvent()
}
