package com.example.backup.dbconfig.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class DbConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var dbUser: String,
    var dbName: String,
    var password: String,
    var host: String,
    var port: Int,
    var backupFilePath: String,
    var isActive: Boolean
)
