package com.nakul.user.controller

import com.nakul.user.model.Address
import com.nakul.user.service.AddressService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/address")
class AddressController {

    @Autowired
    private lateinit var addressService: AddressService

    @GetMapping("")
    fun getAddress(httpRequest: HttpServletRequest): List<Address> {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.read(userId = userId)
    }

    @GetMapping("/{id}")
    fun getAddressById(httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int): Address {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.read(userId = userId, id = addressId)
    }

    @PutMapping("/{id}")
    @Transactional
    fun updateAddress(
        httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int, @RequestBody data: Map<String, Any>
    ): Address {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.update(userId = userId, addressId = addressId, data = data)
    }

    @PostMapping("")
    @Transactional
    fun saveAddress(
        httpRequest: HttpServletRequest, @RequestBody data: Map<String, Any>
    ): Address {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.save(userId = userId, data = data)
    }


    @DeleteMapping("/{id}")
    @Transactional
    fun deleteAddress(
        httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int
    ): Address {
        val userId = (httpRequest.getAttribute("userId") as String).toInt()
        return addressService.delete(userId = userId, addressId = addressId)
    }

}