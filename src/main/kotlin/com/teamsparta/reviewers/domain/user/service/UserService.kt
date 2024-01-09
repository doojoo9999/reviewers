package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.user.dto.request.SingUpRequest
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse

interface UserService {

    fun singUp(email: String, request: SingUpRequest): UserResponse
}