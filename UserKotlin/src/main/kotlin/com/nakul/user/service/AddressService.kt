package com.nakul.user.service

import com.nakul.user.dto.BaseResponse
import com.nakul.user.model.Address
import com.nakul.user.model.User
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


    fun create(userId: Int,address: Address): ResponseEntity<BaseResponse<Address>> {
        val newAddress = addressRepo.save(address)

        val response = BaseResponse(
            response = newAddress,
            status = HttpStatus.OK.value(),
            message = HttpStatus.OK.reasonPhrase,
        )
        return ResponseEntity.ok(response)
    }

    fun read(userId: Int,): ResponseEntity<BaseResponse<List<Address>>> {
        val response = BaseResponse(response = addressRepo.findAll().ifEmpty { null })
        return ResponseEntity.ok(response)
    }

    fun read(userId: Int,id: Int): ResponseEntity<BaseResponse<Address>> {
        val user = addressRepo.findById(id).getOrNull() ?: throw NoSuchElementException()

        val response: BaseResponse<Address> = BaseResponse(user)
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun update(userId: Int, userModel: User?): ResponseEntity<BaseResponse<Address>> {
        val user = addressRepo.findById(userId).getOrNull() ?: throw NoSuchElementException()

        val response: BaseResponse<Address> = BaseResponse(addressRepo.save(user))

        return ResponseEntity.ok(response)
    }

    fun delete(id: Int?): ResponseEntity<BaseResponse<Address>> {
        val response: BaseResponse<Address> = when {
            id == null || addressRepo.findById(id).getOrNull() == null -> {
                throw NoSuchElementException()
            }

            else -> {
                val address = addressRepo.findById(id).getOrNull()
                addressRepo.deleteById(id)
                BaseResponse(address)
            }
        }

        return ResponseEntity.ok(response)
    }
}