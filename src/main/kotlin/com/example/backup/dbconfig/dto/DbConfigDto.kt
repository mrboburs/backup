package com.example.backup.dbconfig.dto

import com.example.backup.dbconfig.model.DbConfig

data class DbConfigDto(
    var id: Long,
    val dbUser: String,
    val dbName: String,
    val password: String,
    val host: String,
    val port: Int,
    val backupFilePath: String,
    val isActive: Boolean
)


