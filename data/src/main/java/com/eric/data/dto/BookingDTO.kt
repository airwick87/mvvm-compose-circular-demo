package com.eric.data.dto

import com.google.gson.annotations.SerializedName

data class BookingDTO(
    val car: CarDTO,
    @SerializedName("subscription_miles_left") val subMilesLeft: String,
)

data class CarDTO(
    @SerializedName("last_energy_level") val lastEnergyLevel: String,
)
