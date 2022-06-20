package com.task.chargestationapp.data.repository

import android.util.Log
import com.task.chargestationapp.data.mapper.toChargeStation
import com.task.chargestationapp.data.remote.ChargeMapApi
import com.task.chargestationapp.domain.model.ChargingStation
import com.task.chargestationapp.domain.repository.ChargingStationRepository
import com.task.chargestationapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChargingStationRepositoryImpl
    @Inject constructor(private val api: ChargeMapApi, private val dispatcher: CoroutineDispatcher) : ChargingStationRepository {

    override suspend fun getChargingStations(): Flow<Resource<List<ChargingStation>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val remoteData = api.getChargingStations()
                val data = remoteData.map {
                    it.toChargeStation()
                }
                emit(Resource.Success(data = data))
            } catch (ex :  Exception) {
                Log.e("Error : ", ex.localizedMessage)
                emit(Resource.Error(ex.localizedMessage))
            }
        }
    }
}