package com.eric.domain.repositories

import com.eric.domain.models.UserDomainModel
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository : BaseRepository{
    suspend fun getUser(): ResultResponse<UserDomainModel>
}