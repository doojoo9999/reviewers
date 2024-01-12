package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.user.dto.request.CreateUserRequest
import com.teamsparta.reviewers.domain.user.dto.request.LoginRequest
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


interface UserService{
    fun signUp(createUserRequest: CreateUserRequest) : UserResponse

    fun login(loginRequest : LoginRequest) : UserResponse

}