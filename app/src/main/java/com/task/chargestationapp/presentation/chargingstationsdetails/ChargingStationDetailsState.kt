package com.task.chargestationapp.presentation.chargingstationsdetails

import com.task.chargestationapp.domain.model.ChargingStation

data class ChargingStationDetailsState(
    var chargingStation: ChargingStation? = null,
    val isLoading: Boolean = false,
    val error: Int? = null
)