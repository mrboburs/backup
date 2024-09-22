package com.example.backup.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true, nullable = false)
    val email: String,
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role,

)

