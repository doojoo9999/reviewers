package com.teamsparta.reviewers.domain.user.security

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TokenController(private val jwtTokenProvider: JwtTokenProvider) {

    data class TokenValidationRequest(val token: String)

    data class TokenValidationResponse(val message: String)

    @PostMapping("/validateToken")
    fun validateToken(@RequestBody request: TokenValidationRequest): TokenValidationResponse {
        try {
            val validatedSubject = jwtTokenProvider.validateToken(request.token)
            return TokenValidationResponse("Token is valid. Subject: $validatedSubject")
        } catch (e: IllegalArgumentException) {
            return TokenValidationResponse("Token validation failed. Reason: ${e.message}")
        }
    }
}