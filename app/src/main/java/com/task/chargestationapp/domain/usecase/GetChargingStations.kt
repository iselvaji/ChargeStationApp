package com.task.chargestationapp.domain.usecase

import com.task.chargestationapp.domain.model.ChargingStation
import com.task.chargestationapp.domain.repository.ChargingStationRepository
import com.task.chargestationapp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetChargingStations(private val repository: ChargingStationRepository){

   suspend operator fun invoke() : Flow<Resource<List<ChargingStation>>> {
        return repository.getChargingStations()
    }
}
