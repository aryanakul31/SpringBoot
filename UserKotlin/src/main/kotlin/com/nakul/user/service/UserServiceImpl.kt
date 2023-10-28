package com.nakul.user.service

import com.nakul.user.dao.UserDao
import com.nakul.user.model.BaseResponse
import com.nakul.user.model.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userDao: UserDao

    override fun create(user: UserModel): ResponseEntity<BaseResponse<UserModel?>> {
        user.email = user.email.lowercase()
        val response: BaseResponse<UserModel?> = when {
            userDao.findByEmail(user.email) != null -> {
                BaseResponse(
                    response = null,
                    responseCode = HttpStatus.BAD_REQUEST.value(),
                    message = "Email already exists",
                    httpStatus = HttpStatus.BAD_REQUEST,
                )
            }

            else -> {
                user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())

                val newUser = userDao.save(user)
                userDao.save(user)
                BaseResponse(
                    response = newUser,
                    responseCode = HttpStatus.OK.value(),
                    message = HttpStatus.OK.reasonPhrase,
                    httpStatus = HttpStatus.OK,
                )
            }
        }
        return ResponseEntity(response, response.httpStatus)
    }

    override fun read(): ResponseEntity<BaseResponse<List<UserModel?>>> {
        val response = BaseResponse(
            response = userDao.findAll(),
            responseCode = HttpStatus.OK.value(),
            message = HttpStatus.OK.reasonPhrase,
            httpStatus = HttpStatus.OK,
        )

        return ResponseEntity(response, response.httpStatus)
    }

    override fun read(id: Int): ResponseEntity<BaseResponse<UserModel?>> {
        val user = userDao.findById(id).getOrNull()
        val responseCode = if (user != null) HttpStatus.OK else HttpStatus.BAD_REQUEST
        val response: BaseResponse<UserModel?> = BaseResponse(
            response = user,
            responseCode = responseCode.value(),
            message = responseCode.reasonPhrase,
            httpStatus = responseCode,
        )
        return ResponseEntity(response, response.httpStatus)
    }

    override fun update(userModel: UserModel?): ResponseEntity<BaseResponse<UserModel?>> {
        val response: BaseResponse<UserModel?> = when {
            userModel == null || userDao.findById(userModel.id).getOrNull() == null -> {
                BaseResponse(
                    response = null,
                    responseCode = HttpStatus.BAD_REQUEST.value(),
                    message = HttpStatus.BAD_REQUEST.reasonPhrase,
                    httpStatus = HttpStatus.BAD_REQUEST,
                )
            }

            else -> {
                BaseResponse(
                    response = userDao.save(userModel),
                    responseCode = HttpStatus.OK.value(),
                    message = HttpStatus.OK.reasonPhrase,
                    httpStatus = HttpStatus.OK,
                )
            }
        }

        return ResponseEntity(response, response.httpStatus)
    }

    override fun delete(id: Int?): ResponseEntity<BaseResponse<UserModel?>> {
        val response: BaseResponse<UserModel?> = when {
            id == null || userDao.findById(id).getOrNull() == null -> {
                BaseResponse(
                    response = null,
                    responseCode = HttpStatus.BAD_REQUEST.value(),
                    message = HttpStatus.BAD_REQUEST.reasonPhrase,
                    httpStatus = HttpStatus.BAD_REQUEST,
                )
            }

            else -> {
                val user = userDao.findById(id).getOrNull()
                userDao.deleteById(id)
                BaseResponse(
                    response = user,
                    responseCode = HttpStatus.OK.value(),
                    message = HttpStatus.OK.reasonPhrase,
                    httpStatus = HttpStatus.OK,
                )
            }
        }

        return ResponseEntity(response, response.httpStatus)
    }

    override fun login(email: String, password: String): ResponseEntity<BaseResponse<UserModel?>> {
        val user = kotlin.runCatching {
            userDao.findByEmail(email)
        }.getOrNull()

        val result: BaseResponse<UserModel?> = when {
            BCrypt.checkpw(password, user?.password) -> BaseResponse(
                response = user,
                responseCode = HttpStatus.OK.value(),
                message = HttpStatus.OK.reasonPhrase,
                httpStatus = HttpStatus.OK,
            )

            else -> BaseResponse(
                response = null,
                responseCode = HttpStatus.BAD_REQUEST.value(),
                message = HttpStatus.OK.reasonPhrase,
                httpStatus = HttpStatus.BAD_REQUEST,
            )
        }
        return ResponseEntity(result, result.httpStatus)
    }
}