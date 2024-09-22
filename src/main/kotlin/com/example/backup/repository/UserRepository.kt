package com.example.backup.repository

import com.example.backup.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : JpaRepository<User, Long>{


    // Method to find a user by their email
    fun findByEmail(email: String): Optional<User>


//    @Query("SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
//    fun findUserByEmail(@Param("email") email: String): Optional<User>
}