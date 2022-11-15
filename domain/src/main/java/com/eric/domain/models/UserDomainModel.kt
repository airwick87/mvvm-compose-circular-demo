package com.eric.domain.models

data class UserDomainModel(
    val username: String,
    val subMilesLeft: String,
    val lastEnergyLevel: String
)

fun UserDomainModel.getMilesFloat() =
    if (subMilesLeft == "0") 0.0f else subMilesLeft.toFloat() / 1000


fun UserDomainModel.getEnergyFloat() =
    if (lastEnergyLevel == "0") 0.0f else lastEnergyLevel.toFloat() / 100

