package com.teamsparta.reviewers.domain.user.dto.response

data class UserResponse(
    val password: String,
    val email: String,
    val birth: String,
    val userName: String,
)