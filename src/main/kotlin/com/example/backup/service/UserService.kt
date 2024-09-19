package com.example.backup.service

import com.example.backup.entity.User
import com.example.backup.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun save(product: User): User = userRepository.save(product)

    fun deleteById(id: Long) = userRepository.deleteById(id)
}