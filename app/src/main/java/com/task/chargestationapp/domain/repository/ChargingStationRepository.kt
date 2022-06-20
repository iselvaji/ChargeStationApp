package com.task.chargestationapp.domain.repository

import com.task.chargestationapp.domain.model.ChargingStation
import com.task.chargestationapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChargingStationRepository {
    suspend fun getChargingStations(): Flow<Resource<List<ChargingStation>>>
}