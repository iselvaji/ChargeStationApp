package com.task.chargestationapp.data.repository

import com.task.chargestationapp.data.mapper.toChargeStation
import com.task.chargestationapp.domain.model.ChargingStation
import com.task.chargestationapp.domain.repository.ChargingStationRepository
import com.task.chargestationapp.util.Resource
import com.task.chargestationapp.utils.TestChargingStationData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeChargingStationRepository : ChargingStationRepository {

    override suspend fun getChargingStations(): Flow<Resource<List<ChargingStation>>> {
        return flow {
            emit(Resource.Loading(true))
            delay(100L)
            val testData = TestChargingStationData.getChargingStations()
            val data = testData.map {
                it.toChargeStation()
            }
            emit(Resource.Success(data))
        }
    }
}