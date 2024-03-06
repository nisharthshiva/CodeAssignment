package com.demo.codeassignment.data.network

import com.demo.codeassignment.common.*
import com.demo.codeassignment.data.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.PHOTO_LIST)
    suspend fun getpicsumlist(@Query("page") page: Int): UserDataListModel

}