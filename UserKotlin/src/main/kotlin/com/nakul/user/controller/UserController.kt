package com.nakul.user.controller

import com.nakul.user.dto.UserDTO
import com.nakul.user.entities.User
import com.nakul.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/user")
    fun get(): List<User> = userService.read()

    @GetMapping("/user/{id}")
    fun get(@PathVariable id: Int): User = userService.read(id)

    @PutMapping("/user/{id}")
    @Transactional
    fun update(@PathVariable id: Int, @RequestBody user: User?): User =
        userService.update(id, user)


    @DeleteMapping("/user/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): User = userService.delete(id)


    @PostMapping("/login")
    @Transactional
    fun login(@RequestBody requestData: Map<String, Any>): UserDTO? {
        val email = requestData["email"] as String
        val password = requestData["password"] as String
        return userService.login(email, password)
    }

    @PostMapping("/signUp")
    @Transactional
    fun signUp(@RequestBody user: User): User {
        return userService.create(user)
    }
}