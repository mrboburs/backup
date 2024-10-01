package com.example.backup.dbconfig.dto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class CreateDbConfigDto(
    val id: Long, // ID is optional

    @field:NotBlank(message = "Database user must not be blank")
    val dbUser: String,

    @field:NotBlank(message = "Database name must not be blank")
    val dbName: String,

    @field:NotBlank(message = "Host must not be blank")
    val host: String,

    @field:NotBlank(message = "Password must not be blank")
    val password: String,

    @field:NotNull(message = "Port must not be null")
    @field:Positive(message = "Port must be a positive number")
    val port: Int,

    @field:NotBlank(message = "Backup file path must not be blank")
    val backupFilePath: String,

    @field:NotNull(message = "isActive must not be null")
    val isActive: Boolean
)
