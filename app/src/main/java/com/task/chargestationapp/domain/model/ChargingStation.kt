package com.task.chargestationapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChargingStation(
    val title: String?,
    val address: Address?,
    val numberOfPoints: String?
) : Parcelable

@Parcelize
data class Address(
    val addressLine1 : String?,
    val addressLine2 : String?,
    val town : String?,
    val stateOrProvince : String?,
    val country : String?,
    val latitude : Double?,
    val longitude : Double?,
): Parcelable