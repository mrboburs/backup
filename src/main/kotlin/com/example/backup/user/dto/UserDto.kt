package com.example.backup.user.dto

import com.example.backup.user.model.Role

class UserDto(
    val id: Long,
    val email: String,
    val password: String,
    val role: Role,
) {
}