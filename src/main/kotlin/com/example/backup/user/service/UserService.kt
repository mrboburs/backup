package com.example.backup.user.service

import com.example.backup.user.dto.CreateUserDto
import com.example.backup.user.dto.UpdateUserDto
import com.example.backup.user.dto.UserDto
import com.example.backup.user.model.User
import com.example.backup.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


interface UserService  {
    fun findById(id: Long): User

    fun updateUserById(
        id: Long,
        dto: UpdateUserDto
    ): UserDto

    fun findAll(): List<UserDto>

    fun deleteById(id: Long)

    fun createUser(createUserDto: CreateUserDto): CreateUserDto


}