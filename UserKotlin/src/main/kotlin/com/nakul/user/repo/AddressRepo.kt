package com.nakul.user.repo

import com.nakul.user.entities.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepo : JpaRepository<Address, Int> {

    fun findByUser_UserId(userId: Int): List<Address>
    fun findByUser_UserIdAndAddressId(userId: Int, addressId: Int): Address?

}

