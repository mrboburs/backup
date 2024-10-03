package com.example.backup.user.controller


import com.example.backup.user.dto.CreateUserDto
import com.example.backup.user.dto.UpdateUserDto
import com.example.backup.util.ResponseData
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/users")
interface UserController {

  @PostMapping()
    fun createUser(
    @Valid @RequestBody user: CreateUserDto,
    ): ResponseEntity<ResponseData>


    @PutMapping("/{id}")
    fun updateUserById(
        @PathVariable id: Long,
        @Valid @RequestBody  dto: UpdateUserDto
    ): ResponseEntity<ResponseData>


    @GetMapping
    fun getAllUsers(
    ): ResponseEntity<ResponseData>


    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Long
    ): ResponseEntity<ResponseData>


    // Delete an dbConfig by ID
    @DeleteMapping("/{id}")
    fun deleteUserById(
        @PathVariable id: Long
    ): ResponseEntity<ResponseData>
}