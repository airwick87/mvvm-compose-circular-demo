package com.eric.data.mappers

import com.eric.data.dto.UserDTO
import com.eric.domain.models.UserDomainModel

fun UserDTO.mapToDomain(): UserDomainModel =
    UserDomainModel(
        username = username,
        subMilesLeft = bookings.first().subMilesLeft,
        lastEnergyLevel = bookings.first().car.lastEnergyLevel
    )