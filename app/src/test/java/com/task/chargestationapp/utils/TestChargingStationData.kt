package com.task.chargestationapp.utils

import com.task.chargestationapp.data.remote.dto.AddressInfo
import com.task.chargestationapp.data.remote.dto.ChargeStationDto
import com.task.chargestationapp.data.remote.dto.Country

object TestChargingStationData {

    fun getChargingStations(): ArrayList<ChargeStationDto> {

        val data  = ArrayList<ChargeStationDto>()

        val chargingStation1 = ChargeStationDto(iD = 1,
            addressInfo = AddressInfo(
                iD = 1,
                title = "TestTitle1",
                addressLine1 = "TestAddressLine1",
                addressLine2 = "TestAddressLine1",
                town = "TestTown1",
                stateOrProvince = "TestState1",
                postcode = 12345,
                countryID = 123,
                country = Country("1","2",1,"TestCountry1"),
                latitude = 12.34,
                longitude = 12.34,
                contactTelephone1 = "1234",
                contactTelephone2 = "1234",
                contactEmail = "test1@abc.com",
                accessComments = "Test comments1",
                relatedURL = "",
                distance = 5.0,
                distanceUnit = 1
            ), numberOfPoints = "1")

        val chargingStation2 = ChargeStationDto(iD = 2,
            addressInfo = AddressInfo(
                iD = 2,
                title = "TestTitle2",
                addressLine1 = "TestAddressLine2",
                addressLine2 = "TestAddressLine2",
                town = "TestTown2",
                stateOrProvince = "TestState2",
                postcode = 12345,
                countryID = 123,
                country = Country("2","2",2,"TestCountry2"),
                latitude = 12.34,
                longitude = 12.34,
                contactTelephone1 = "1234",
                contactTelephone2 = "1234",
                contactEmail = "test2@abc.com",
                accessComments = "Test comments2",
                relatedURL = "",
                distance = 5.0,
                distanceUnit = 1
            ), numberOfPoints = "1")

        data.add(chargingStation1)
        data.add(chargingStation2)

        return data
    }
}