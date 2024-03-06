package com.demo.codeassignment.common


sealed class BaseError {
    object NetworkError : BaseError()
    object UnauthorizedError : BaseError()
    data class ServerError(val responseBody: String?) : BaseError()
    object UnknownError : BaseError()
}
