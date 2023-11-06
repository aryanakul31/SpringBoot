package com.nakul.user.service

//import org.springframework.security.crypto.bcrypt.BCrypt
import com.nakul.user.dto.request.CreateUserRequestDTO
import com.nakul.user.dto.request.LoginRequestDTO
import com.nakul.user.dto.response.UserResponseDTO
import com.nakul.user.entities.User
import com.nakul.user.misc.EmailAlreadyExistsException
import com.nakul.user.misc.InvalidPasswordException
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


    fun create(createUserDTO: CreateUserRequestDTO): UserResponseDTO {
        if (userRepo.findByEmail(createUserDTO.email).getOrNull() != null) throw EmailAlreadyExistsException()


        val user = User(
            name = createUserDTO.name,
            email = createUserDTO.email.lowercase(),
            password = createUserDTO.password, //BCrypt.hashpw(createUserDTO.password, BCrypt.gensalt())
        )
        return userRepo.save(user).getMap(true)
    }

    fun read(): List<UserResponseDTO> {
        val userList = userRepo.findAll()
        return userList.map {
            it.getMap()
        }
    }

    fun read(userId: Int): UserResponseDTO {
        return userRepo.findById(userId).get().getMap()
    }


    fun update(userId: Int, createUserDTO: CreateUserRequestDTO): UserResponseDTO {
        val user = userRepo.findById(userId).get()
        if (createUserDTO.email.isNotBlank()) user.email = createUserDTO.email.lowercase()
        if (createUserDTO.password.isNotBlank()) user.password = createUserDTO.password
        if (createUserDTO.name.isNotBlank()) user.name = createUserDTO.name

        return userRepo.save(user).getMap()
    }

    fun delete(id: Int): UserResponseDTO {
        val user = userRepo.findById(id).get()
        userRepo.deleteById(id)
        return user.getMap()
    }

    fun login(loginDTO: LoginRequestDTO): UserResponseDTO {
        val user = userRepo.findByEmail(loginDTO.email).get()

        when {
            user.password == loginDTO.password/*BCrypt.checkpw(password, user?.password)*/ -> {
                return user.getMap(true)
            }

            else -> throw InvalidPasswordException()
        }
    }

    @Autowired
    private lateinit var mapper: ModelMapper

    @Autowired
    private lateinit var addressService: AddressService

    fun User.getMap(token: Boolean = false): UserResponseDTO {
        val data = mapper.map(this, UserResponseDTO::class.java)
        if (token) data.token = JwtUtil.generateToken(this)

        data.addresses = addressService.read(userId)

        return data
    }
}