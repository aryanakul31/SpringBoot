package com.nakul.user.service

//import org.springframework.security.crypto.bcrypt.BCrypt
import com.nakul.user.dto.request.CreateUserRequestDTO
import com.nakul.user.dto.request.LoginRequestDTO
import com.nakul.user.entities.User
import com.nakul.user.misc.EmailAlreadyExistsException
import com.nakul.user.misc.InvalidPasswordException
import com.nakul.user.misc.UserNotFoundException
import com.nakul.user.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import kotlin.jvm.optionals.getOrNull

@Service
@Validated
class UserService {

    @Autowired
    private lateinit var userRepo: UserRepo



    fun create(createUserDTO: CreateUserRequestDTO): User {
        if (userRepo.findByEmail(createUserDTO.email).getOrNull() != null)
            throw EmailAlreadyExistsException()


        val user = User(
            name = createUserDTO.name,
            email = createUserDTO.email.lowercase(),
            password = createUserDTO.password, //BCrypt.hashpw(createUserDTO.password, BCrypt.gensalt())
        )
        return userRepo.save(user)
    }

    fun read(): List<User> {
        return userRepo.findAll()
    }

    fun read(userId: Int): User {
        return userRepo.findById(userId).getOrNull() ?: throw NoSuchElementException()
    }


    fun update(userId: Int, createUserDTO: CreateUserRequestDTO): User {
        val user = userRepo.findById(userId).getOrNull() ?: throw UserNotFoundException()
        if (createUserDTO.email.isNotBlank())
            user.email = createUserDTO.email.lowercase()
        if (createUserDTO.password.isNotBlank())
            user.password = createUserDTO.password
        if (createUserDTO.name.isNotBlank())
            user.name = createUserDTO.name


        userRepo.save(user)
        return user
    }

    fun delete(id: Int): User {
        val user = userRepo.findById(id).get()
        userRepo.deleteById(id)
        return user
    }

    fun login(loginDTO: LoginRequestDTO): User {
        val user = userRepo.findByEmail(loginDTO.email).getOrNull() ?: throw UserNotFoundException()

        when {
            user.password == loginDTO.password/*BCrypt.checkpw(password, user?.password)*/ -> {
                return user
            }

            else -> throw InvalidPasswordException()
        }
    }
}