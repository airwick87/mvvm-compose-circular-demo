package com.eric.data.services

import com.eric.data.dto.BookingDTO
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    //https://raw.githubusercontent.com/rentELtd/iostest/master/user.json
    @GET("rentELtd/iostest/master/user.json")
    suspend fun getUser(): Response<BookingDTO>
}
