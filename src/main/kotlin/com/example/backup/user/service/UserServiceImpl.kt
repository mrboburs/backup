package com.example.backup.user.service


import com.example.backup.exception.DuplicateEntityException
import com.example.backup.exception.EntityNotFoundException
import com.example.backup.user.dto.CreateUserDto
import com.example.backup.user.dto.UpdateUserDto
import com.example.backup.user.dto.UserDto
import com.example.backup.user.model.User
import com.example.backup.user.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
):UserService {

    override fun createUser(createUserDto: CreateUserDto): CreateUserDto {

        val user = User(
            email = createUserDto.email,
            password = createUserDto.password,
            role = createUserDto.role,
        )
        try {
       // Initialize the PasswordEncoder (assuming you're using BCryptPasswordEncoder)
            val encoder: PasswordEncoder = BCryptPasswordEncoder()
            // Encode the user's password
            val encodedPassword = encoder.encode(user.password)
            // Set the encoded password back to the user object
            val userWithEncodedPassword = user.copy(password = encodedPassword)
            val savedUser = userRepository.save(userWithEncodedPassword)
            return CreateUserDto(

                email = savedUser.email,

                password = savedUser.password,
                role = savedUser.role,

            )
        } catch (ex: DataIntegrityViolationException) {
            throw DuplicateEntityException(
            )
        }

    }


    override fun deleteById(id: Long) {
        val existingUser = userRepository.findById(id)
            .orElseThrow { EntityNotFoundException() }
        // Perform the delete operation
        userRepository.delete(existingUser)
    }
    override  fun findAll(): List<UserDto> {
        return userRepository.findAll().map { user ->
            UserDto(
                id = user.id,
                email = user.email,
                password = user.password,
                role=user.role


            )
        }
    }

    override fun findById(id: Long): User {

        return userRepository.findById(id).orElseThrow { EntityNotFoundException() }
    }

    override fun updateUserById(
        id: Long,
        dto: UpdateUserDto
    ): UserDto {
        val existingUser = userRepository.findById(id)
            .orElseThrow { EntityNotFoundException() }

        // Update fields only if they are not null
        dto.email?.let { existingUser.email = it }

        dto.password?.let { existingUser.password = it }
        dto.role?.let { existingUser.role = it }


        return try {
            userRepository.save(existingUser).toDto()
        } catch (ex: DataIntegrityViolationException) {
            throw DuplicateEntityException()
        }

    }
    private fun User.toDto(): UserDto = UserDto(
         id=this.id,
        email = this.email,
        password = this.password,
        role = this.role,

    )
    // Conversion logic (optional to keep as a separate function)
}