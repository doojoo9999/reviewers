package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignUpRequest
import com.teamsparta.reviewers.domain.user.dto.request.UserUpdateRequest
import com.teamsparta.reviewers.domain.user.dto.response.SignInResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import com.teamsparta.reviewers.domain.user.dto.response.UserUpdateResponse

interface UserService {

    fun signUp(email: String, request: SignUpRequest): SignUpResponse

    fun signIn(request: SignInRequest): SignInResponse

    fun userUpdate(email: String, request: UserUpdateRequest): UserUpdateResponse
}