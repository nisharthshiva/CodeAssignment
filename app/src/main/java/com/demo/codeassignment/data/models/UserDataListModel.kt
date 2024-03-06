package com.demo.codeassignment.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


class UserDataListModel(
    var isDataLoaded :Boolean = true,
    var data: List<UserDetailsModel>?=null
) : java.io.Serializable

class UserDetailsModel(
    var id:Int?=null,
    var email:String?= null,
    var first_name:String?= null,
    var last_name:String?= null,
    var avatar:String?= null
) : java.io.Serializable

@Entity
class UserDetailsDB(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var email:String?= null,
    var first_name:String?= null,
    var last_name:String?= null,
    var avatar:String?= null
) {
    constructor(email: String?,first_name: String?,last_name: String?,avatar: String?):
            this(0,email, first_name, last_name, avatar)
}