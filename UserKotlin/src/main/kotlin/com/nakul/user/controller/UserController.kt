package com.nakul.user.controller

import com.nakul.user.dto.response.UserResponseDTO
import com.nakul.user.dto.request.CreateUserRequestDTO
import com.nakul.user.dto.request.LoginRequestDTO
import com.nakul.user.entities.User
import com.nakul.user.repo.AddressRepo
import com.nakul.user.security.JwtUtil
import com.nakul.user.service.UserService
import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/user")
    fun get(): List<UserResponseDTO> = userService.read().map { it.getDto() }

    @GetMapping("/user/{id}")
    fun get(@PathVariable("id") userId: Int): UserResponseDTO = userService.read(userId = userId).getDto()

    @PutMapping("/user/{id}")
    @Transactional
    fun update(@PathVariable("id") userId: Int, @RequestBody createUserDTO: CreateUserRequestDTO): UserResponseDTO =
        userService.update(userId = userId, createUserDTO = createUserDTO).getDto()


    @DeleteMapping("/user/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): UserResponseDTO = userService.delete(id).getDto()


    @PostMapping("/login")
    @Transactional
    fun login(@RequestBody loginDTO: LoginRequestDTO): UserResponseDTO? {
        return userService.login(loginDTO = loginDTO).getDto(true)
    }

    @PostMapping("/signUp")
    @Transactional
    fun signUp(@Valid @RequestBody createUserDTO: CreateUserRequestDTO): UserResponseDTO {
        return userService.create(createUserDTO).getDto(true)
    }

    @Autowired
    lateinit var addressRepo: AddressRepo

    fun User.getDto(token: Boolean = false): UserResponseDTO {
        val mapper = ModelMapper()
        val data = mapper.map(this, UserResponseDTO::class.java)
        if (token)
            data.token = JwtUtil.generateToken(this)

        data.addresses = addressRepo.findByUserId(userId).toSet()
        return data
    }

}