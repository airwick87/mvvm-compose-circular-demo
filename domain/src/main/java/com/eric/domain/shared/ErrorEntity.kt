package com.eric.domain.shared

sealed class ErrorEntity(val message: String) {

    object NoError : ErrorEntity("") {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class NetworkFailure(message: String = "network failure") : ErrorEntity(message) {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class Generic(message: String = "Generic error") : ErrorEntity(message) {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class Unknown(message: String = "Unknown error") : ErrorEntity(message) {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

}