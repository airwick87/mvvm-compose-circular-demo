package com.eric.data.repositories

import com.eric.data.mappers.mapToDomain
import com.eric.data.services.UserService
import com.eric.domain.models.UserDomainModel
import com.eric.domain.repositories.UserRepository
import com.eric.domain.shared.ErrorEntity
import com.eric.domain.shared.ResultResponse

class UserRepositoryImpl(
    private val userService: UserService,
) : UserRepository {
    override suspend fun getUser(): ResultResponse<UserDomainModel> =
        safeApiCall {
            userService.getUser().run {
                this.body()?.let {
                    if (isSuccessful) {
                        ResultResponse.Success(it.mapToDomain())
                    } else {
                        //TODO: Custom error code handling if req
                        ResultResponse.Failure(ErrorEntity.Generic())
                    }
                } ?: ResultResponse.Failure(ErrorEntity.Unknown())

            }
        }


}