package com.teamsparta.reviewers.domain.user.controller

import com.teamsparta.reviewers.domain.user.common.UserRole
import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignUpRequest
import com.teamsparta.reviewers.domain.user.dto.request.UserUpdateRequest
import com.teamsparta.reviewers.domain.user.dto.response.SignInResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignOutResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import com.teamsparta.reviewers.domain.user.dto.response.UserUpdateResponse
import com.teamsparta.reviewers.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
    private val encoder: PasswordEncoder
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
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signIn(request))
    }

    // 회원정보 수정
    @PutMapping()
    fun userUpdate(@RequestBody @Valid request: UserUpdateRequest, email: String): ResponseEntity<UserUpdateResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.userUpdate(email, request))
    }

}