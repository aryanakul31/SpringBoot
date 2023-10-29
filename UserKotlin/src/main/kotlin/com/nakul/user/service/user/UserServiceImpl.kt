package com.nakul.user.service.user

//import org.springframework.security.crypto.bcrypt.BCrypt
import com.nakul.user.dto.BaseResponse
import com.nakul.user.exceptions.EmailAlreadyExistsException
import com.nakul.user.exceptions.InvalidPasswordException
import com.nakul.user.exceptions.UserNotFoundException
import com.nakul.user.model.UserModel
import com.nakul.user.repo.user.UserRepo
import com.nakul.user.secruity.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userDao: UserRepo

    override fun create(user: UserModel): ResponseEntity<BaseResponse<UserModel?>> {
        user.email = user.email.lowercase()
        val response: BaseResponse<UserModel?> = when {
            userDao.findByEmail(user.email).getOrNull() != null -> {
                throw EmailAlreadyExistsException()
            }

            else -> {
//                user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
                val newUser = userDao.save(user)
                userDao.save(user)
                BaseResponse(
                    response = newUser,
                    status = HttpStatus.OK.value(),
                    message = HttpStatus.OK.reasonPhrase,
                )
            }
        }
        return ResponseEntity.ok(response)
    }

    override fun read(): ResponseEntity<BaseResponse<List<UserModel?>>> {
        val response = BaseResponse(response = userDao.findAll().ifEmpty { null })
        return ResponseEntity.ok(response)
    }

    override fun read(id: Int): ResponseEntity<BaseResponse<UserModel?>> {
        val user = userDao.findById(id).getOrNull() ?: throw NoSuchElementException()

        val response: BaseResponse<UserModel?> = BaseResponse(user)
        return ResponseEntity(response, HttpStatus.OK)
    }

    override fun update(userId: Int, userModel: UserModel?): ResponseEntity<BaseResponse<UserModel?>> {
        val user =
            userDao.findById(userId).getOrNull() ?: throw UserNotFoundException()

        val response: BaseResponse<UserModel?> = BaseResponse(userDao.save(user))

        return ResponseEntity.ok(response)
    }

    override fun delete(id: Int?): ResponseEntity<BaseResponse<UserModel?>> {
        val response: BaseResponse<UserModel?> = when {
            id == null || userDao.findById(id).getOrNull() == null -> {
                throw UserNotFoundException()
            }

            else -> {
                val user = userDao.findById(id).getOrNull()
                userDao.deleteById(id)
                BaseResponse(user)
            }
        }

        return ResponseEntity.ok(response)
    }

    override fun login(email: String, password: String): ResponseEntity<BaseResponse<UserModel?>> {
        val user = userDao.findByEmail(email).getOrNull() ?: throw UserNotFoundException()


        when {
            user.password == password/*BCrypt.checkpw(password, user?.password)*/ -> {
                user.token = JwtUtil.generateToken(user)
                val response: BaseResponse<UserModel?> = BaseResponse(user)
                return ResponseEntity.ok(response)
            }

            else ->
                throw InvalidPasswordException()
        }
    }
}