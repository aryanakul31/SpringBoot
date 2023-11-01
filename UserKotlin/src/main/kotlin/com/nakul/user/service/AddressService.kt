package com.nakul.user.service

import com.nakul.user.dto.BaseResponse
import com.nakul.user.model.Address
import com.nakul.user.repo.AddressRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class AddressService {

    @Autowired
    private lateinit var addressRepo: AddressRepo


    fun create(userId: Int, address: Address): ResponseEntity<BaseResponse<Address>> {
        val newAddress = addressRepo.save(address)

        val response = BaseResponse(
            response = newAddress,
            status = HttpStatus.OK.value(),
            message = HttpStatus.OK.reasonPhrase,
        )
        return ResponseEntity.ok(response)
    }

    fun read(userId: Int): ResponseEntity<BaseResponse<List<Address>>> {

        val response = BaseResponse(response = addressRepo.findAll().ifEmpty { null })
        return ResponseEntity.ok(response)
    }

    fun read(userId: Int, id: Int): ResponseEntity<BaseResponse<Address>> {
        val user = addressRepo.findById(id).getOrNull() ?: throw NoSuchElementException()

        val response: BaseResponse<Address> = BaseResponse(user)
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun save(userId: Int, data: Map<String, Any>): ResponseEntity<BaseResponse<Address>> {
        val address = Address(
            name = data["name"] as String,
            lat = data["lat"] as Double,
            long = data["long"] as Double,
            userId = userId,
        )

        addressRepo.save(address)
        val response = BaseResponse(addressRepo.save(address))

        return ResponseEntity.ok(response)
    }

    fun update(userId: Int, addressId: Int, data: Map<String, Any>): ResponseEntity<BaseResponse<Address>> {
        val address = addressRepo.findByIdAndUserId(id = addressId, userId = userId)
        if (data.containsKey("name")) address.name = data["name"] as String
        if (data.containsKey("lat")) address.lat = data["lat"] as Double
        if (data.containsKey("long")) address.long = data["long"] as Double

        val response = BaseResponse(addressRepo.save(address))

        return ResponseEntity.ok(response)
    }

    fun delete(userId: Int, addressId: Int): ResponseEntity<BaseResponse<Address>> {
        val address = addressRepo.findByIdAndUserId(addressId, userId)

        addressRepo.deleteById(address.id)
        val response = BaseResponse(address)
        return ResponseEntity.ok(response)
    }
}