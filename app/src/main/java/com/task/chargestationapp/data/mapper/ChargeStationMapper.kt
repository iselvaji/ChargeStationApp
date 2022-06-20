package com.task.chargestationapp.data.mapper

import com.task.chargestationapp.data.remote.dto.ChargeStationDto
import com.task.chargestationapp.domain.model.Address
import com.task.chargestationapp.domain.model.ChargingStation

fun ChargeStationDto.toChargeStation(): ChargingStation {
    return ChargingStation(
        title = addressInfo?.title,
        numberOfPoints = numberOfPoints,
        address = Address(
            addressLine1 = addressInfo?.addressLine1,
            addressLine2 = addressInfo?.addressLine2,
            town = addressInfo?.town,
            stateOrProvince = addressInfo?.stateOrProvince,
            country = addressInfo?.country?.title,
            latitude = addressInfo?.latitude,
            longitude = addressInfo?.longitude)
    )
}