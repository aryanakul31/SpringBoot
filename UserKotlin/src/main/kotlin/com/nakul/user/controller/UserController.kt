package com.nakul.user.controller

import com.nakul.user.dto.BaseResponse
import com.nakul.user.model.UserModel
import com.nakul.user.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/user")
    fun get(): ResponseEntity<BaseResponse<List<UserModel?>>> = userService.read()

    @GetMapping("/user/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<BaseResponse<UserModel?>> = userService.read(id)

    @PutMapping("/user/{id}")
    @Transactional
    fun update(@PathVariable id: Int, @RequestBody user: UserModel?): ResponseEntity<BaseResponse<UserModel?>> =
        userService.update(id, user)


    @DeleteMapping("/user/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): ResponseEntity<BaseResponse<UserModel?>> = userService.delete(id)


    @PostMapping("/login")
    @Transactional
    fun login(@RequestBody requestData: Map<String, Any>): ResponseEntity<BaseResponse<UserModel?>> {
        val email = requestData["email"] as String
        val password = requestData["password"] as String
        return userService.login(email, password)
    }

    @PostMapping("/signUp")
    @Transactional
    fun signUp(@RequestBody user: UserModel): ResponseEntity<BaseResponse<UserModel?>> {
        return userService.create(user)
    }
}