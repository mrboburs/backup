package com.example.backup.repository

import com.example.backup.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository



@Repository
interface UserRepository : JpaRepository<User, Long>