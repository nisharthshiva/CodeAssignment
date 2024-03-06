package com.demo.codeassignment.common

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(private val gson: Gson) : ErrorHandler {

    override fun getErrorType(throwable: Throwable): BaseError {
        return when (throwable) {
            is IOException -> {
                return BaseError.NetworkError
            }
            is HttpException -> {
                return when (throwable.code()) {
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                        BaseError.NetworkError
                    }
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        BaseError.UnauthorizedError
                    }
                    else -> {
                        val response = throwable.response()
                        val error = response?.errorBody()?.string()
                        error?.let {
                            val jsonObject = gson.fromJson(it, JsonObject::class.java)
                            if (jsonObject.size() > 0) {
                                val jsonElementMessage = jsonObject.get("message")
                                if (jsonElementMessage is JsonElement) {
                                    val errorInfo = jsonElementMessage.toString()
                                    BaseError.ServerError(errorInfo)
                                } else {
                                    val errorUtils = gson.fromJson(error, ErrorUtils::class.java)
                                    BaseError.ServerError(errorUtils.message)
                                }
                            } else {
                                BaseError.UnknownError
                            }
                        } ?: BaseError.UnknownError
                    }
                }
            }
            else -> {
                BaseError.UnknownError
            }
        }
    }
}