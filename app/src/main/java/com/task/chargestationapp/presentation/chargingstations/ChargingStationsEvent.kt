package com.task.chargestationapp.presentation.chargingstations

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.task.chargestationapp.domain.model.ChargingStation

sealed class ChargingStationsEvent {
    object PollData: ChargingStationsEvent()
    object StopPollingData: ChargingStationsEvent()
    data class OnMapMarkerClick(val station: ChargingStation, val navigator: DestinationsNavigator): ChargingStationsEvent()
}
