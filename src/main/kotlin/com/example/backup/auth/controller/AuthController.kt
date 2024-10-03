package com.example.backup.auth.controller

import com.example.backup.auth.dto.AuthenticationRequest
import com.example.backup.auth.dto.AuthenticationResponse
import com.example.backup.auth.dto.RefreshTokenRequest
import com.example.backup.auth.dto.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@RequestMapping("/auth")
interface AuthController {
    @PostMapping()
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequest
    ): AuthenticationResponse



    @PostMapping("refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest
    ): TokenResponse
}
