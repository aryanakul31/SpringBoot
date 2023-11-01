package com.nakul.user.repo

import com.nakul.user.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepo : JpaRepository<Address, Int> {
    fun findByUserId(userId: Int): List<Address>
    fun findByIdAndUserId(id: Int, userId: Int): Address
}

