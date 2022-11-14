package com.eric.data.services

import com.eric.data.dto.UserDTO
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("rentELtd/iostest/master/user.json")
    suspend fun getUser(): Response<UserDTO>
}
