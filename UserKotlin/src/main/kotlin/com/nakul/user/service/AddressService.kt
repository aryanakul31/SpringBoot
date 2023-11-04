package com.nakul.user.service

import com.nakul.user.dto.request.AddressRequestDTO
import com.nakul.user.entities.Address
import com.nakul.user.repo.AddressRepo
import com.nakul.user.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class AddressService {

    @Autowired
    private lateinit var addressRepo: AddressRepo

    @Autowired
    private lateinit var userRepo: UserRepo


    fun read(userId: Int): Set<Address> {
        return addressRepo.findByUserId(id = userId)
    }

    fun read(userId: Int, id: Int): Address {
        return addressRepo.findByUserIdAndAddressId(userId = userId, addressId = id) ?: throw NoSuchElementException()
    }

    fun save(userId: Int, addressRequestDTO: AddressRequestDTO): Address {
        val address = Address(
            name = addressRequestDTO.name,
            lat = addressRequestDTO.lat,
            long = addressRequestDTO.long,
            description = addressRequestDTO.description,
            userId = userId,
        )

        return addressRepo.save(address)
    }

    fun update(userId: Int, addressId: Int, addressRequestDTO: AddressRequestDTO): Address {
        val address = addressRepo.findByUserIdAndAddressId(addressId = addressId, userId = userId)
            ?: throw NoSuchElementException()

        if (addressRequestDTO.name.isNotBlank())
            address.name = addressRequestDTO.name
        if (addressRequestDTO.description.isNotBlank())
            address.description = addressRequestDTO.description
        if (addressRequestDTO.lat != null)
            address.lat = addressRequestDTO.lat
        if (addressRequestDTO.long != null)
            address.long = addressRequestDTO.long

        return addressRepo.save(address)
    }

    fun delete(userId: Int, addressId: Int): Address {

        val address = addressRepo.findByUserIdAndAddressId(addressId = addressId, userId = userId)
            ?: throw NoSuchElementException()
        addressRepo.deleteById(address.addressId)
        return address
    }
}