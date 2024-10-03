package com.example.backup.user.dto

import com.example.backup.user.model.Role
import jakarta.validation.constraints.NotBlank

class UpdateUserDto(

    var email: String?,
    var password: String?,
    var role: Role?,
)