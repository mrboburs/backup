package com.example.backup.user.service

import com.example.backup.user.model.User
import com.example.backup.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun save(user: User): User {
        // Initialize the PasswordEncoder (assuming you're using BCryptPasswordEncoder)
        val encoder: PasswordEncoder = BCryptPasswordEncoder()

        // Encode the user's password
        val encodedPassword = encoder.encode(user.password)

        // Set the encoded password back to the user object
        val userWithEncodedPassword = user.copy(password = encodedPassword)

        // Save the user to the repository
        return userRepository.save(userWithEncodedPassword)
    }

    fun deleteById(id: Long) = userRepository.deleteById(id)
}