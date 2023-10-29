package com.nakul.user.service.user

import com.nakul.user.dto.BaseResponse
import com.nakul.user.model.UserModel
import org.springframework.http.ResponseEntity

interface UserService {
    fun create(user: UserModel): ResponseEntity<BaseResponse<UserModel?>>


    fun read(): ResponseEntity<BaseResponse<List<UserModel?>>>
    fun read(id: Int): ResponseEntity<BaseResponse<UserModel?>>


    fun update(userId:Int,userModel: UserModel? = null): ResponseEntity<BaseResponse<UserModel?>>

    fun delete(id: Int?): ResponseEntity<BaseResponse<UserModel?>>

    fun login(email: String, password: String): ResponseEntity<BaseResponse<UserModel?>>
}