package com.nakul.user.controller

import com.nakul.user.dto.BaseResponse
import com.nakul.user.model.Address
import com.nakul.user.service.AddressService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/address")
class AddressController {

    @Autowired
    private lateinit var addressService: AddressService

    @GetMapping("")
    fun get(httpRequest: HttpServletRequest): ResponseEntity<BaseResponse<List<Address>>> {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.read(userId = userId)
    }

    @GetMapping("/{id}")
    fun get(httpRequest: HttpServletRequest, @PathVariable id: Int): ResponseEntity<BaseResponse<Address>> {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.read(userId = userId, id)
    }

    @PutMapping("/{id}")
    @Transactional
    fun update(
        httpRequest: HttpServletRequest,
        @PathVariable("id") addressId: Int,
        @RequestBody data: Map<String, Any>
    ): ResponseEntity<BaseResponse<Address>> {

        val userId = (httpRequest.getAttribute("userId") as String).toInt()

        return addressService.update(userId = userId, addressId = addressId, data = data)
    }

    @PostMapping("")
    @Transactional
    fun save(
        httpRequest: HttpServletRequest,
        @RequestBody data: Map<String, Any>
    ): ResponseEntity<BaseResponse<Address>> {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.save(userId = userId, data = data)
    }


    @DeleteMapping("/{id}")
    @Transactional
    fun delete(
        httpRequest: HttpServletRequest,
        @PathVariable("id") addressId: Int
    ): ResponseEntity<BaseResponse<Address>> {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.delete(userId = userId, addressId = addressId)
    }

}