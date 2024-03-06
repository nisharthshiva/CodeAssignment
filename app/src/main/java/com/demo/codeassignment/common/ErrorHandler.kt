package com.demo.codeassignment.common


interface ErrorHandler {
    fun getErrorType(throwable: Throwable): BaseError
}