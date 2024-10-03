package com.example.backup.dbconfig.repository



import com.example.backup.dbconfig.dto.DbConfigDto
import com.example.backup.dbconfig.model.DbConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DbConfigRepository : JpaRepository<DbConfig, Long>{


    @Query("SELECT d FROM DbConfig d WHERE LOWER(d.dbName) LIKE LOWER(CONCAT('%', :query, '%'))")
    fun findByDbName(@Param("query") query: String): List<DbConfig>


    // Custom query using @Query annotation
    @Query("SELECT d FROM DbConfig d WHERE d.isActive = true")
    fun findAllActive(): List<DbConfig>
}



