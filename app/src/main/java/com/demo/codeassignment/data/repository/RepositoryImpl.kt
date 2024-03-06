package com.demo.codeassignment.data.repository

import android.content.Context
import androidx.room.RoomDatabase
import com.demo.codeassignment.base.BaseResponse
import com.demo.codeassignment.common.*
import com.demo.codeassignment.data.models.*
import com.demo.codeassignment.data.network.ApiService
import com.demo.codeassignment.room_db.MyRoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val roomDatabase : MyRoomDatabase,
    private val errorHandler: ErrorHandler,
    private val context: Context
) : Repository {

    override suspend fun getUserDatalist(page: Int): BaseResponse<UserDataListModel> {
        return try {
            if (Utility.isInternetAvailable(context)){
                val result = apiService.getpicsumlist(page)
                if ((result.data?:ArrayList()).isNotEmpty()){
                    val existingEmails = roomDatabase.getRoomDatabase().getUserData().map {
                        it.email
                    }
                    val uniqueEmails = result.data?.filter {
                        !existingEmails.contains(it.email)
                    }?:ArrayList()
                    roomDatabase.getRoomDatabase().insertUserData(uniqueEmails.map {
                        UserDetailsDB(it.email,it.first_name,it.last_name,it.avatar)
                    })
                }else{
                    result.isDataLoaded = false
                }
                BaseResponse.Success(result)
            }else{
                val result = UserDataListModel()
                result.isDataLoaded = false
                result.data =  roomDatabase.getRoomDatabase().getUserData().map {
                    UserDetailsModel(it.id.toInt(), it.email, it.first_name, it.last_name, it.avatar)
                }
                BaseResponse.Success(result)
            }
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }

}