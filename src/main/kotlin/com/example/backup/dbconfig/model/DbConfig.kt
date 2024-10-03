package com.example.backup.dbconfig.model

import jakarta.persistence.*



@Entity
data class DbConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var dbUser: String,
    var dbName: String,


    @Column(unique = true) // Ensure password is unique
    var password: String,
    var host: String,
    var port: Int,
    var backupFilePath: String,
    var isActive: Boolean
)
