package com.demo.codeassignment.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.codeassignment.data.models.UserDetailsDB

@Database(entities = [UserDetailsDB::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun getRoomDatabase() : Dao
}