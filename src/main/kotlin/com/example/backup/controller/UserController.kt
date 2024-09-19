package com.example.backup.controller

import com.example.backup.entity.User
import com.example.backup.service.UserService

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(private val userService: UserService) {

    // Create a new user
    @PostMapping
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<Any> {
        val createdUser = userService.save(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }

    // Get all users
    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        val users = userService.findAll()
        return ResponseEntity.ok(users)
    }

    // Get a user by ID
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Any> {
        val user = userService.findById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            val errorBody = mapOf("error" to "User with ID $id not found")
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody)
        }
    }

    // Update a user by ID
    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @Valid @RequestBody updatedUser: User
    ): ResponseEntity<Any> {
        val existingUser = userService.findById(id)
        return if (existingUser != null) {
            val userToUpdate = existingUser.copy(
                email = updatedUser.email,
                password = updatedUser.password
                ,role = updatedUser.role)
            ResponseEntity.ok(userService.save(userToUpdate))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        val user = userService.findById(id)
        return if (user != null) {
            userService.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
