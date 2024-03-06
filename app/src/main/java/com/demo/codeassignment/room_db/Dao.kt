package com.demo.codeassignment.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.codeassignment.data.models.UserDetailsDB

@Dao
interface Dao {
    @Insert
    suspend fun insertUserData(userData : List<UserDetailsDB>)

    @Query("SELECT * FROM userdetailsdb")
    suspend fun getUserData() : List<UserDetailsDB>
}