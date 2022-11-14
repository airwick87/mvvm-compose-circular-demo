package com.eric.domain.repositories

import com.eric.domain.models.UserDomainModel
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): Flow<ResultResponse<UserDomainModel>>
}