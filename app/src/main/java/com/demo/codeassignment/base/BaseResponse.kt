package com.demo.codeassignment.base

import com.demo.codeassignment.common.BaseError


sealed class BaseResponse<LT> {
    data class Success<T>(val data: T) : BaseResponse<T>()
    data class Error<T>(val error: BaseError) : BaseResponse<T>()
}