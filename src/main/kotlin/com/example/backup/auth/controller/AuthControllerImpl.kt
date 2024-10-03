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
class AuthControllerImpl(
    private val authenticationService: AuthenticationService
):AuthController {


   override fun authenticate(
       authRequest: AuthenticationRequest
    ): AuthenticationResponse {
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
