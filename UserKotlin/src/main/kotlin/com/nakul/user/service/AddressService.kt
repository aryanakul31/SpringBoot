package com.nakul.user.service

import com.nakul.user.dto.request.AddressRequestDTO
import com.nakul.user.dto.response.AddressResponseDTO
import com.nakul.user.entities.Address
import com.nakul.user.repo.AddressRepo
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressService {

    @Autowired
    private lateinit var addressRepo: AddressRepo


    fun read(userId: Int): List<AddressResponseDTO> {
        return addressRepo.findByUserId(id = userId).map {
            it.getMap()
        }
    }

    fun read(userId: Int, id: Int): AddressResponseDTO {
        return addressRepo.findByUserIdAndAddressId(userId = userId, addressId = id)?.getMap()
            ?: throw NoSuchElementException()
    }

    fun save(userId: Int, addressRequestDTO: AddressRequestDTO): AddressResponseDTO {
        val address = Address(
            name = addressRequestDTO.name,
            lat = addressRequestDTO.lat,
            long = addressRequestDTO.long,
            description = addressRequestDTO.description,
            userId = userId,
        )

        return addressRepo.save(address).getMap()
    }

    fun update(userId: Int, addressId: Int, addressRequestDTO: AddressRequestDTO): AddressResponseDTO {
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

        return addressRepo.save(address).getMap()
    }

    fun delete(userId: Int, addressId: Int): AddressResponseDTO {

        val address = addressRepo.findByUserIdAndAddressId(addressId = addressId, userId = userId)
            ?: throw NoSuchElementException()
        addressRepo.deleteById(address.addressId)
        return address.getMap()
    }

    @Autowired
    private lateinit var mapper: ModelMapper

    fun Address.getMap(): AddressResponseDTO {
        val response = mapper.map(this, AddressResponseDTO::class.java)
        return response
    }
}