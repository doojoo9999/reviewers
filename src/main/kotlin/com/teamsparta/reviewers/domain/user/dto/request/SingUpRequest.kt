package com.teamsparta.reviewers.domain.user.dto.request

data class SingUpRequest(
    val password: String,
    val email: String,
    val birth: String,
    val userName: String,
    val profile_Image: String,
)