package com.eric.data.repositories

import com.eric.data.mappers.mapToDomain
import com.eric.data.services.UserService
import com.eric.domain.models.UserDomainModel
import com.eric.domain.repositories.UserRepository
import com.eric.domain.shared.ErrorEntity
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserRepositoryImpl(
    private val userService: UserService,
) : UserRepository {
    override suspend fun getUser(): Flow<ResultResponse<UserDomainModel>> =
        flowOf(userService.getUser().run
        {
            this.body()?.let {
                if (isSuccessful) {
                    ResultResponse.Success(it.mapToDomain())
                } else {
                    //TODO: Custom error code handling if req
                    ResultResponse.Failure(ErrorEntity.Generic())
                }
            } ?: ResultResponse.Failure(ErrorEntity.Unknown())

        })

}