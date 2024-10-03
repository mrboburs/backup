package com.example.backup.dbconfig.dto

data class UpdateDbConfigDto(
    val dbUser: String?,
    val dbName: String?,
    val password: String?,
    val host: String?,
    val port: Int?,
    val backupFilePath: String?,
    val isActive: Boolean?
)
