package com.eric.data.mappers

import com.eric.data.dto.UserDTO
import konveyor.base.randomBuild
import org.junit.Test

class UserMapperTest {
    @Test
    fun mapToDomain() {
        val sut: UserDTO = randomBuild<UserDTO>().copy(bookings = listOf(randomBuild()))
        val result = sut.mapToDomain()
        assert(result.lastEnergyLevel == sut.bookings.first().car.lastEnergyLevel)
        assert(result.username == sut.username)
        assert(result.subMilesLeft == sut.bookings.first().subMilesLeft)
    }
}