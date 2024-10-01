package com.example.backup.dbconfig.repository



import com.example.backup.dbconfig.model.DbConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DbConfigRepository : JpaRepository<DbConfig, Long>
