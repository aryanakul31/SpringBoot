package com.nakul.user.service

//import org.springframework.security.crypto.bcrypt.BCrypt
import com.nakul.user.dto.request.CreateUserRequestDTO
import com.nakul.user.dto.request.LoginRequestDTO
import com.nakul.user.dto.response.UserResponseDTO
import com.nakul.user.entities.User
import com.nakul.user.misc.EmailAlreadyExistsException
import com.nakul.user.misc.InvalidPasswordException
import com.nakul.user.misc.UserNotFoundException
import com.nakul.user.repo.AddressRepo
import com.nakul.user.repo.UserRepo
import com.nakul.user.security.JwtUtil
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import kotlin.jvm.optionals.getOrNull

@Service
@Validated
class UserService {

    @Autowired
    private lateinit var userRepo: UserRepo


    fun create(createUserDTO: CreateUserRequestDTO): UserResponseDTO {
        if (userRepo.findByEmail(createUserDTO.email).getOrNull() != null) throw EmailAlreadyExistsException()


        val user = User(
            name = createUserDTO.name,
            email = createUserDTO.email.lowercase(),
            password = createUserDTO.password, //BCrypt.hashpw(createUserDTO.password, BCrypt.gensalt())
        )
        return userRepo.save(user).getDto(true)
    }

    fun read(): List<UserResponseDTO> {
        return userRepo.findAll().map { it.getDto() }
    }

    fun read(userId: Int): UserResponseDTO {
        return userRepo.findById(userId).getOrNull()?.getDto() ?: throw NoSuchElementException()
    }


    fun update(userId: Int, createUserDTO: CreateUserRequestDTO): UserResponseDTO {
        val user = userRepo.findById(userId).getOrNull() ?: throw UserNotFoundException()
        if (createUserDTO.email.isNotBlank()) user.email = createUserDTO.email.lowercase()
        if (createUserDTO.password.isNotBlank()) user.password = createUserDTO.password
        if (createUserDTO.name.isNotBlank()) user.name = createUserDTO.name


        userRepo.save(user)
        return user.getDto()
    }

    fun delete(id: Int): UserResponseDTO {
        val user = userRepo.findById(id).get()
        userRepo.deleteById(id)
        return user.getDto()
    }

    fun login(loginDTO: LoginRequestDTO): UserResponseDTO {
        val user = userRepo.findByEmail(loginDTO.email).getOrNull() ?: throw UserNotFoundException()

        when {
            user.password == loginDTO.password/*BCrypt.checkpw(password, user?.password)*/ -> {
                return user.getDto(true)
            }

            else -> throw InvalidPasswordException()
        }
    }


    @Autowired
    lateinit var addressRepo: AddressRepo

    fun User.getDto(token: Boolean = false): UserResponseDTO {
        val mapper = ModelMapper()
        val data = mapper.map(this, UserResponseDTO::class.java)
        if (token) data.token = JwtUtil.generateToken(this)

        data.addresses = addressRepo.findByUserId(userId).toSet()
        return data
    }
}