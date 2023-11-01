package com.nakul.user.service

//import org.springframework.security.crypto.bcrypt.BCrypt
import com.nakul.user.dto.BaseResponse
import com.nakul.user.exceptions.EmailAlreadyExistsException
import com.nakul.user.exceptions.InvalidPasswordException
import com.nakul.user.exceptions.UserNotFoundException
import com.nakul.user.model.User
import com.nakul.user.repo.AddressRepo
import com.nakul.user.repo.UserRepo
import com.nakul.user.secruity.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserService {

    @Autowired
    private lateinit var userRepo: UserRepo

    @Autowired
    private lateinit var addressRepo: AddressRepo


    fun create(user: User): ResponseEntity<BaseResponse<User?>> {
        user.email = user.email.lowercase()
        val response: BaseResponse<User?> = when {
            userRepo.findByEmail(user.email).getOrNull() != null -> {
                throw EmailAlreadyExistsException()
            }

            else -> {
//                user.myPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
                val newUser = userRepo.save(user)

                BaseResponse(
                    response = newUser,
                    status = HttpStatus.OK.value(),
                    message = HttpStatus.OK.reasonPhrase,
                )
            }
        }
        return ResponseEntity.ok(response)
    }

    fun read(): ResponseEntity<BaseResponse<List<User?>>> {
        val response = BaseResponse(response = userRepo.findAll().ifEmpty { null })
        return ResponseEntity.ok(response)
    }

    fun read(id: Int): ResponseEntity<BaseResponse<User?>> {
        val user = userRepo.findById(id).getOrNull() ?: throw NoSuchElementException()

        val response: BaseResponse<User?> = BaseResponse(user)
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun update(userId: Int, userModel: User?): ResponseEntity<BaseResponse<User?>> {
        val user = userRepo.findById(userId).getOrNull() ?: throw UserNotFoundException()

        val response: BaseResponse<User?> = BaseResponse(userRepo.save(user))

        return ResponseEntity.ok(response)
    }

    fun delete(id: Int): ResponseEntity<BaseResponse<User>> {
        val user = userRepo.findById(id).get()
        userRepo.deleteById(id)
        return ResponseEntity.ok(BaseResponse(user))
    }

    fun login(email: String, password: String): ResponseEntity<BaseResponse<User?>> {
        val user = userRepo.findByEmail(email).getOrNull() ?: throw UserNotFoundException()


        when {
            user.password == password/*BCrypt.checkpw(password, user?.password)*/ -> {
                user.token = JwtUtil.generateToken(user)
                val response: BaseResponse<User?> = BaseResponse(user)
                return ResponseEntity.ok(response)
            }

            else -> throw InvalidPasswordException()
        }
    }
}