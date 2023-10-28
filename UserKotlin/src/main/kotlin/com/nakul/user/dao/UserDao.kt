package com.nakul.user.dao

import com.nakul.user.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao :JpaRepository<UserModel,Int>{

    fun findByEmail(email: String):UserModel?
    fun findByEmailAndPassword(email: String,password:String):UserModel?
//    fun create(user: UserModel): UserModel?
//
//
//    fun read(): List<UserModel>
//    fun read(id: Int): UserModel?
//
//
//    fun update(userModel: UserModel? = null): UserModel?
//
//    fun delete(id: Int):Boolean
}

