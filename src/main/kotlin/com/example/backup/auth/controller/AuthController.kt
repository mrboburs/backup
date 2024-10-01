package com.example.backup.auth.controller
import com.example.backup.auth.dto.AuthenticationRequest
import com.example.backup.auth.dto.AuthenticationResponse
import com.example.backup.auth.dto.RefreshTokenRequest
import com.example.backup.auth.dto.TokenResponse
import com.example.backup.auth.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
class AuthController(
    private val authenticationService: AuthenticationService

):AuthControllerImpl {


   override fun authenticate(
       authRequest: AuthenticationRequest
    ): AuthenticationResponse {
        println("21")
        return authenticationService.authenticate(authRequest)
    }



     override fun refreshAccessToken(
       request: RefreshTokenRequest
    ): TokenResponse =
        authenticationService.refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token")
    private fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(
            token = this,
        )
}
