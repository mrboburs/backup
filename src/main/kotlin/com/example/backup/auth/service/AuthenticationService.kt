package com.example.backup.auth.service

import com.example.backup.config.JwtProperties
import com.example.backup.auth.dto.AuthenticationRequest
import com.example.backup.auth.dto.AuthenticationResponse
import com.example.backup.auth.repository.RefreshTokenRepository
import com.example.backup.user.repository.UserRepository
import com.example.backup.user.service.CustomUserDetailsService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    val userRepository: UserRepository,
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
 fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
     println(24)
     authManager.authenticate(
         UsernamePasswordAuthenticationToken(
             authRequest.email,
             authRequest.password
         )
     )
     println(30)
     val user1 = userRepository.findByEmail(authRequest.email)
     println(user1)
     val user = userDetailsService.loadUserByUsername(authRequest.email)
     val accessToken= generateAccessToken(user)
     val refreshToken= generateRefreshToken(user)

     refreshTokenRepository.save(refreshToken,user)

     return  AuthenticationResponse(
         accessToken=accessToken,
         refreshToken=refreshToken,
         )
 }
    fun refreshAccessToken(token: String):String?{
        val extractedEmail=tokenService.extractEmail(token )
        return  extractedEmail?.let { email->
            val currentUserDetails=userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails=refreshTokenRepository.findUserDetailsByToken(token)
            if (!tokenService.isExpired(token)&&currentUserDetails.username==refreshTokenUserDetails?.username)
                generateAccessToken(currentUserDetails)
            else null
        }
    }


    private fun generateRefreshToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() +
                jwtProperties.refreshTokenExpiration )
    )

    private fun generateAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() +
                jwtProperties.accessTokenExpiration)
    )

}