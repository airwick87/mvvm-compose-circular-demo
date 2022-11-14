package com.eric.domain.usecases

import com.eric.domain.models.UserDomainModel
import com.eric.domain.repositories.UserRepository
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun getUserDetails(): Flow<ResultResponse<UserDomainModel>> =
        userRepository.getUser()
}


