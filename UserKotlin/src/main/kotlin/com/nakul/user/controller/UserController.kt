package com.nakul.user.controller

import com.nakul.user.dto.BaseResponse
import com.nakul.user.model.User
import com.nakul.user.service.UserService
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
    fun get(): ResponseEntity<BaseResponse<List<User?>>> = userService.read()

    @GetMapping("/user/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<BaseResponse<User?>> = userService.read(id)

    @PutMapping("/user/{id}")
    @Transactional
    fun update(@PathVariable id: Int, @RequestBody user: User?): ResponseEntity<BaseResponse<User?>> =
        userService.update(id, user)


    @DeleteMapping("/user/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): ResponseEntity<BaseResponse<User?>> = userService.delete(id)


    @PostMapping("/login")
    @Transactional
    fun login(@RequestBody requestData: Map<String, Any>): ResponseEntity<BaseResponse<User?>> {
        val email = requestData["email"] as String
        val password = requestData["password"] as String
        return userService.login(email, password)
    }

    @PostMapping("/signUp")
    @Transactional
    fun signUp(@RequestBody user: User): ResponseEntity<BaseResponse<User?>> {
        return userService.create(user)
    }
}