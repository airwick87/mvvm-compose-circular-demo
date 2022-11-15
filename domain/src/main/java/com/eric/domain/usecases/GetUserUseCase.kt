package com.eric.domain.usecases

import com.eric.domain.models.UserDomainModel
import com.eric.domain.repositories.UserRepository
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun getUserDetails(): Flow<ResultResponse<UserDomainModel>> =
        flowOf(userRepository.getUser())
}


