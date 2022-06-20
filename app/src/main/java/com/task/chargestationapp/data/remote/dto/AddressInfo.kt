package com.task.chargestationapp.data.remote.dto

import com.squareup.moshi.Json

data class AddressInfo(

    @field:Json(name ="ID") val iD : Int?,
    @field:Json(name ="Title") val title : String?,
    @field:Json(name ="AddressLine1") val addressLine1 : String?,
    @field:Json(name ="AddressLine2") val addressLine2 : String?,
    @field:Json(name ="Town") val town : String?,
    @field:Json(name ="StateOrProvince") val stateOrProvince : String?,
    @field:Json(name ="Postcode") val postcode : Int?,
    @field:Json(name ="CountryID") val countryID : Int?,
    @field:Json(name ="Country") val country : Country?,
    @field:Json(name ="Latitude") val latitude : Double?,
    @field:Json(name ="Longitude") val longitude : Double?,
    @field:Json(name ="ContactTelephone1") val contactTelephone1 : String?,
    @field:Json(name ="ContactTelephone2") val contactTelephone2 : String?,
    @field:Json(name ="ContactEmail") val contactEmail : String?,
    @field:Json(name ="AccessComments") val accessComments : String?,
    @field:Json(name ="RelatedURL") val relatedURL : String?,
    @field:Json(name ="Distance") val distance : Double?,
    @field:Json(name ="DistanceUnit") val distanceUnit : Int?
)

data class Country (

    @field:Json(name ="ISOCode") val iSOCode : String?,
    @field:Json(name ="ContinentCode") val continentCode : String?,
    @field:Json(name ="ID") val iD : Int?,
    @field:Json(name ="Title") val title : String?
)