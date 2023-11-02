package com.nakul.user.service

//import org.springframework.security.crypto.bcrypt.BCrypt
import com.nakul.user.dto.UserDTO
import com.nakul.user.entities.User
import com.nakul.user.misc.EmailAlreadyExistsException
import com.nakul.user.misc.InvalidPasswordException
import com.nakul.user.misc.UserNotFoundException
import com.nakul.user.repo.UserRepo
import com.nakul.user.security.JwtUtil
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserService {

    @Autowired
    private lateinit var userRepo: UserRepo

    fun create(user: User): User {
        user.email = user.email.lowercase()

        when {
            userRepo.findByEmail(user.email).getOrNull() != null -> {
                throw EmailAlreadyExistsException()
            }

            else -> {
//                user.myPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
                return userRepo.save(user)
            }
        }
    }

    fun read(): List<User> {
        return userRepo.findAll()
    }

    fun read(id: Int): User {
        return userRepo.findById(id).getOrNull() ?: throw NoSuchElementException()
    }

    fun update(userId: Int, userModel: User?): User {
        val user = userRepo.findById(userId).getOrNull() ?: throw UserNotFoundException()
        userRepo.save(user)
        return user
    }

    fun delete(id: Int): User {
        val user = userRepo.findById(id).get()
        userRepo.deleteById(id)
        return user
    }

    fun login(email: String, password: String): UserDTO? {
        val user = userRepo.findByEmail(email).getOrNull() ?: throw UserNotFoundException()

        when {
            user.password == password/*BCrypt.checkpw(password, user?.password)*/ -> {
                val mapper = ModelMapper()
                val data = mapper.map(user, UserDTO::class.java)
                data.token = JwtUtil.generateToken(user)
                return data
            }

            else -> throw InvalidPasswordException()
        }
    }
}