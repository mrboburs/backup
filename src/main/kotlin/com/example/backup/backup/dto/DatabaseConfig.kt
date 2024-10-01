package com.example.backup.backup.dto

data class DatabaseConfig(
    val dbUser: String,
    val dbPassword: String,
    val dbName: String,
    val host: String,
    val port: String,
    val backupFilePath: String
)