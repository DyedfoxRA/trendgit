package com.venture.core.domain.results

sealed interface DataError : BaseError {

    enum class Network : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL
    }

    enum class Validation: DataError {
        FIELD_NOT_EMPTY
    }
}