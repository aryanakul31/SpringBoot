package com.nakul.user.controller

import com.nakul.user.model.BaseResponse
import com.nakul.user.model.UserModel
import com.nakul.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService


    @GetMapping("")
    @Transactional
    fun get(): ResponseEntity<BaseResponse<List<UserModel?>>> {
        userService.read()

        return userService.read()
    }

    @GetMapping("/{id}")
    @Transactional
    operator fun get(@PathVariable id: Int): ResponseEntity<BaseResponse<UserModel?>> {
        return userService.read(id)
    }

    @PutMapping("")
    @Transactional
    fun update(@RequestBody user: UserModel?): ResponseEntity<BaseResponse<UserModel?>> {
        return userService.update(user)
    }

    @PostMapping("")
    @Transactional
    fun save(@RequestBody user: UserModel): ResponseEntity<BaseResponse<UserModel?>> {
        return userService.create(user)
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): String {
        userService.delete(id)
        return "Employee Deleted"
    }


    @PostMapping("/login")
    @Transactional
    fun login(@RequestBody requestData: Map<String, Any>): ResponseEntity<BaseResponse<UserModel?>> {
        val email = requestData["email"] as String
        val password = requestData["password"] as String
        return userService.login(email, password)
    }
}