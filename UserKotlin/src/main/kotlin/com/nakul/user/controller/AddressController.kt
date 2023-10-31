package com.nakul.user.controller

import com.nakul.user.dto.BaseResponse
import com.nakul.user.model.Address
import com.nakul.user.model.User
import com.nakul.user.service.AddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/address")
class AddressController {

    @Autowired
    private lateinit var addressService: AddressService

//    @GetMapping("")
//    fun get(httpRequest: HttpRequest): ResponseEntity<BaseResponse<List<Address>>> = addressService.read()
//
//    @GetMapping("/{id}")
//    fun get(@PathVariable id: Int): ResponseEntity<BaseResponse<Address>> = addressService.read(id)

    @PutMapping("/{id}")
    @Transactional
    fun update(@PathVariable id: Int, @RequestBody user: User?): ResponseEntity<BaseResponse<Address>> =
        addressService.update(id, user)


    @DeleteMapping("/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): ResponseEntity<BaseResponse<Address>> = addressService.delete(id)

}