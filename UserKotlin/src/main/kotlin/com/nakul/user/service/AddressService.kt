package com.nakul.user.service

import com.nakul.user.model.Address
import com.nakul.user.repo.AddressRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class AddressService {

    @Autowired
    private lateinit var addressRepo: AddressRepo


    fun read(userId: Int): List<Address> {
        return addressRepo.findAll()
    }

    fun read(userId: Int, id: Int): Address {
        return addressRepo.findById(id).getOrNull() ?: throw NoSuchElementException()
    }

    fun save(userId: Int, data: Map<String, Any>): Address {
        val address = Address(
            name = data["name"] as String,
            lat = data["lat"] as Double,
            long = data["long"] as Double,
            userId = userId,
        )

        return addressRepo.save(address)
    }

    fun update(userId: Int, addressId: Int, data: Map<String, Any>): Address {
        val address = addressRepo.findByIdAndUserId(id = addressId, userId = userId)
        if (data.containsKey("name")) address.name = data["name"] as String
        if (data.containsKey("lat")) address.lat = data["lat"] as Double
        if (data.containsKey("long")) address.long = data["long"] as Double

        return addressRepo.save(address)
    }

    fun delete(userId: Int, addressId: Int): Address {
        val address = addressRepo.findByIdAndUserId(addressId, userId)
        addressRepo.deleteById(address.id)
        return address
    }
}