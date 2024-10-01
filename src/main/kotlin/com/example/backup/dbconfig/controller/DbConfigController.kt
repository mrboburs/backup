package com.example.backup.dbconfig.controller


import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import com.example.backup.dbconfig.service.DbConfigService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class DbConfigController(
    private val dbConfigService: DbConfigService
) : DbConfigControllerImpl {

    // Create a new dbConfig
    override fun createDbConfig(
        dbConfig: CreateDbConfigDto,
    ): ResponseEntity<Any> {
        val createdDbConfig = dbConfigService.createDbConfig(dbConfig)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDbConfig)
    }


    //     Update an article by ID
    override fun updateDbConfigById(
        id: Long,
         updatedDbConfig: UpdateDbConfigDto
    ): ResponseEntity<Any> {
        return dbConfigService.updateDbConfigById(id, updatedDbConfig)?.let {
            ResponseEntity.ok(it) // Return the updated dbConfig
        } ?: ResponseEntity.notFound().build() // Return 404 if not found
    }

    // Delete an dbConfig by ID
    override fun deleteDbConfigById(
        id: Long
    ): ResponseEntity<Void> {
        val dbConfig = dbConfigService.findById(id)
        return if (dbConfig != null) {
            dbConfigService.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }


    // Get an dbConfig by ID
    override fun getDbConfigById(
        id: Long
    ): ResponseEntity<Any> {
        val dbConfig = dbConfigService.findById(id)
        return if (dbConfig != null) {
            ResponseEntity.ok(dbConfig)
        } else {
            val errorBody = mapOf("error" to "DbConfig with ID $id not found")
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody)
        }
    }

    // Get all dbconfig
    override fun getAllDbConfigs(
    ): ResponseEntity<Any> {
        val dbConfigs = dbConfigService.findAll()
        return ResponseEntity.ok(dbConfigs)
    }

}
