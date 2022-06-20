package com.task.chargestationapp.presentation.chargingstations

import com.task.chargestationapp.domain.model.ChargingStation

data class ChargingStationState(
    val chargingStations : List<ChargingStation>? = emptyList(),
    val isLoading: Boolean = false,
    val error: Int? = null
)