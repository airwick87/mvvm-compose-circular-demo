package com.eric.data.dto

data class UserDTO(
    val username: String,
    val bookings: List<BookingDTO>,
    val email: String,
)


