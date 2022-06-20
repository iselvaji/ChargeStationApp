package com.task.chargestationapp.data.remote.dto

import com.squareup.moshi.Json

data class ChargeStationDto (
    @field:Json(name ="ID") val iD : Int?,
    @field:Json(name ="AddressInfo") val addressInfo : AddressInfo?,
    @field:Json(name ="NumberOfPoints") val numberOfPoints : String?,
)