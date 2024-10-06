package com.example.backup.dbconfig.controller

import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import com.example.backup.util.ResponseData
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RequestMapping("/dbconfigs")
interface DbConfigController {

    @PostMapping
    fun createDbConfig(
        @Valid @RequestBody dbConfig: CreateDbConfigDto,
    ): ResponseEntity<ResponseData>


    @PutMapping("/{id}")
    fun updateDbConfigById(
        @PathVariable  id: Long,
        @Valid @RequestBody  dto: UpdateDbConfigDto
    ): ResponseEntity<ResponseData>

   @GetMapping("/active")
   fun getActiveDbConfigs(
   ): ResponseEntity<ResponseData>


   @GetMapping("/search")
   fun findByDbName(
     @RequestParam query: String
   ): ResponseEntity<ResponseData>

    @GetMapping
    fun getAllDbConfigs(
    ): ResponseEntity<ResponseData>


    @GetMapping("/{id}")
    fun getDbConfigById(
        @PathVariable id: Long
    ): ResponseEntity<ResponseData>


    // Delete an dbConfig by ID
    @DeleteMapping("/{id}")
     fun deleteDbConfigById(
      @PathVariable  id: Long
    ): ResponseEntity<ResponseData>
}


