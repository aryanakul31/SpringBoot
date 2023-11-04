package com.nakul.user.repo

import com.nakul.user.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo : JpaRepository<User, Int> {

    fun findByEmail(email: String?): Optional<User>
//    fun findByUsername(username: String): UserModel?
}

