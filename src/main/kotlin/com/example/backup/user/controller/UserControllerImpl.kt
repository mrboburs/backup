package com.example.backup.user.controller


import com.example.backup.user.dto.CreateUserDto
import com.example.backup.user.dto.UpdateUserDto
import com.example.backup.user.service.UserService
import com.example.backup.util.ResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController

class UserControllerImpl (
    private val userService: UserService
):UserController {



    //     Update an article by ID
    override fun updateUserById(
        id: Long,
        dto: UpdateUserDto
    ): ResponseEntity<ResponseData> {
        var res= userService.updateUserById(id, dto)
        return ResponseEntity.ok(ResponseData(res))
    }


    // Delete an user by ID
    override fun deleteUserById(
        id: Long
    ): ResponseEntity<ResponseData> {
        val user = userService.deleteById(id)
        return  ResponseEntity.ok(ResponseData(
            user,
        ))
    }


    // Get an user by ID
    override fun getUserById(
        id: Long
    ): ResponseEntity<ResponseData> {
        val user = userService.findById(id)
        return   ResponseEntity.ok(ResponseData(
            user,
        ))
    }


    // Get all user
    override fun getAllUsers(
    ): ResponseEntity<ResponseData> {
        val users = userService.findAll()
        return ResponseEntity.ok(ResponseData(users))
    }


    // Create a new user
    override fun createUser(
        user: CreateUserDto,
    ): ResponseEntity<ResponseData> {
        var res =userService.createUser(user)
        return ResponseEntity.ok(
            ResponseData(res)
        )
    }



}
