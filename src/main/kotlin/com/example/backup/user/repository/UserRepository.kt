package com.example.backup.user.repository

import com.example.backup.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : JpaRepository<User, Long>{


    // Method to find a user by their email
    fun findByEmail(email: String): Optional<User>


//    @Query("SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
//    fun findUserByEmail(@Param("email") email: String): Optional<User>
}