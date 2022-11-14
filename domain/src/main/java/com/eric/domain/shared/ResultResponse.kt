package com.eric.domain.shared

sealed class ResultResponse<T> {
    data class Success<T>(val data: T) : ResultResponse<T>()
    class Failure<T>(val error: ErrorEntity) : ResultResponse<T>()
    class Loading<T>() : ResultResponse<T>()

    fun isSuccess(): Boolean = this is Success<*>
    fun isFailure(): Boolean = this is Failure
    fun isLoading(): Boolean = this is Loading

    fun extractData(): T = with(this) {
        if (!isSuccess()) {
            throw IllegalStateException()
        }

        (this as Success<T>).data
    }

    fun extractError() = with(this) {
        if (!isFailure()) {
            ErrorEntity.NoError
        } else {
            (this as Failure<T>).error
        }
    }
}

