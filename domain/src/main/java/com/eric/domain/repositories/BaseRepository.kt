package com.eric.domain.repositories

import com.eric.domain.shared.ErrorEntity
import com.eric.domain.shared.ResultResponse
import java.io.IOException

interface BaseRepository {
    suspend fun <T> safeApiCall(function: suspend () -> ResultResponse<T>): ResultResponse<T> {
        return try {
            function.invoke()
        } catch (e: Exception) {
            when (e) {
                is IOException -> {
                    ResultResponse.Failure(ErrorEntity.NetworkFailure())
                }
                else -> ResultResponse.Failure(ErrorEntity.Unknown())
            }

        }
    }
}
