package com.example.backup.user.dto

import com.example.backup.user.model.Role
import jakarta.validation.constraints.NotBlank


class CreateUserDto(
    @field:NotBlank(message = " Email must not be blank")
    val email: String,

    @field:NotBlank(message = "Password must not be blank")
    val password: String,

    @field:NotBlank(message = "Role must not be blank")
    val role: Role,

) {

}