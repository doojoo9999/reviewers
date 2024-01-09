package com.teamsparta.reviewers.domain.user.dto.request

import java.time.LocalDate

data class CreateUserRequest(
    val email: String,
    val password: String,
    val username: String,
    val birth: LocalDate,
)
