package com.nakul.user.controller

//import com.nakul.user.entities.Address
import com.nakul.user.dto.request.AddressRequestDTO
import com.nakul.user.dto.response.AddressResponseDTO
import com.nakul.user.entities.Address
import com.nakul.user.service.AddressService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/address")
class AddressController {

    @Autowired
    private lateinit var addressService: AddressService

    @GetMapping("")
    fun getAddress(httpRequest: HttpServletRequest): List<AddressResponseDTO> {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return addressService.read(userId = userId).map { it.getMap() }
    }

    @GetMapping("/{id}")
    fun getAddressById(httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int): AddressResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return addressService.read(userId = userId, id = addressId).getMap()
    }

    @PutMapping("/{id}")
    @Transactional
    fun updateAddress(
        httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int,
        @RequestBody addressRequestDTO: AddressRequestDTO,
    ): AddressResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return addressService.update(userId = userId, addressId = addressId, addressRequestDTO = addressRequestDTO).getMap()
    }

    @PostMapping("")
    @Transactional
    fun saveAddress(
        httpRequest: HttpServletRequest,
        @Valid @RequestBody addressRequestDTO : AddressRequestDTO
    ): AddressResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return addressService.save(userId = userId, addressRequestDTO = addressRequestDTO).getMap()
    }


    @DeleteMapping("/{id}")
    @Transactional
    fun deleteAddress(
        httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int
    ): AddressResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return addressService.delete(userId = userId, addressId = addressId).getMap()
    }

    fun Address.getMap(): AddressResponseDTO {
        val mapper = ModelMapper()
        val response =  mapper.map(this, AddressResponseDTO::class.java)
        return response
    }

}