package com.nakul.user.dao

import com.nakul.user.model.User


interface UserDao {

    fun create(user: User): User?


    fun read(): List<User>?

    fun readById(id: Int): User?


    fun update(id: Int? = null, user: User? = null):User?

    fun delete(id: Int)
}

