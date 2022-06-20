package com.task.chargestationapp.util

import com.task.chargestationapp.domain.model.Address
import com.task.chargestationapp.domain.model.ChargingStation

object TestData {

    val chargingStation = ChargingStation(
        title="TestStation",
        address = Address(
            addressLine1 = "TestAddressLine1",
            addressLine2 = "TestAddressLine1",
            town = "TestTown1",
            stateOrProvince = "TestState1",
            country = "TestCountry1",
            latitude = 12.34,
            longitude = 12.34,
        ), numberOfPoints = "1")
}