package com.example.backup.dbconfig.controller

import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RequestMapping("/dbconfigs")
interface DbConfigControllerImpl {

    @PostMapping
    fun createDbConfig(
        @Valid @RequestBody dbConfig: CreateDbConfigDto,
    ): ResponseEntity<Any>


    @PutMapping("/{id}")
    fun updateDbConfigById(
        @PathVariable  id: Long,
        @Valid @RequestBody  updatedDbConfig: UpdateDbConfigDto
    ): ResponseEntity<Any>


    @GetMapping
    fun getAllDbConfigs(
    ): ResponseEntity<Any>


    @GetMapping("/{id}")
    fun getDbConfigById(
        @PathVariable id: Long
    ): ResponseEntity<Any>


    // Delete an dbConfig by ID
    @DeleteMapping("/{id}")
     fun deleteDbConfigById(
      @PathVariable  id: Long
    ): ResponseEntity<Void>
}


