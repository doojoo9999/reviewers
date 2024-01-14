package com.teamsparta.reviewers.domain.user.controller

import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignUpRequest
import com.teamsparta.reviewers.domain.user.dto.request.UserUpdateRequest
import com.teamsparta.reviewers.domain.user.dto.response.SignInResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import com.teamsparta.reviewers.domain.user.dto.response.UserUpdateResponse
import com.teamsparta.reviewers.domain.user.security.JwtTokenProvider
import com.teamsparta.reviewers.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider
) {

    // 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid signUpRequest: SignUpRequest, email: String): ResponseEntity<SignUpResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(email, signUpRequest))
    }

    // 로그인
    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: SignInRequest): ResponseEntity<SignInResponse> {
        val user = userService.signIn(request)
        val token = jwtTokenProvider.createToken(user.email)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SignInResponse(user.email, user.userName, user.userRole, token))
    }

    // 회원정보 수정
    @PutMapping("/userUpdate")
    fun userUpdate(@RequestBody @Valid request: UserUpdateRequest,
                   email: String
    ): ResponseEntity<UserUpdateResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.userUpdate(email, request))
    }
}