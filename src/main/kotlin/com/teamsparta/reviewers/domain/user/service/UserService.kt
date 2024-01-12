package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.user.dto.request.CreateUserRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse


interface UserService{
    fun signUp(createUserRequest: CreateUserRequest) : UserResponse

    fun signIn(signInRequest : SignInRequest) : UserResponse

}