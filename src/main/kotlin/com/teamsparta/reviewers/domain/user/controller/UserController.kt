package com.teamsparta.reviewers.domain.user.controller

import com.teamsparta.reviewers.domain.user.dto.request.CreateUserRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse
import com.teamsparta.reviewers.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class UserController(
    private val userService: UserService
) {
    // 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid createUserRequest: CreateUserRequest) : ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(createUserRequest))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInRequest : SignInRequest) : ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signIn(signInRequest))

    }


}