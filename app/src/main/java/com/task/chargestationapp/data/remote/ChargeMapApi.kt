package com.task.chargestationapp.data.remote

import com.task.chargestationapp.data.remote.dto.ChargeStationDto
import com.task.chargestationapp.util.Constants.DISTANCE
import com.task.chargestationapp.util.Constants.LATITUDE
import com.task.chargestationapp.util.Constants.LONGITUDE
import retrofit2.http.GET
import retrofit2.http.Query

interface ChargeMapApi {

    @GET("poi")
    suspend fun getChargingStations(
        @Query("key") apiKey: String = API_KEY,
        @Query("latitude") latitude: Double = LATITUDE,
        @Query("longitude") longitude: Double = LONGITUDE,
        @Query("distance") distance: String = DISTANCE,
    ) : List<ChargeStationDto>

    companion object{
        const val API_KEY = "1e2cb9c6-a0e9-4a68-bc09-f3c97a6bd8e4&latitude"
        const val BASE_URL = "https://api.openchargemap.io/v3/"
    }
}

