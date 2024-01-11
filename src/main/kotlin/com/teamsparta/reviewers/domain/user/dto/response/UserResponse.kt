package com.teamsparta.reviewers.domain.user.dto.response

import com.teamsparta.reviewers.domain.user.model.ROLE
import java.time.LocalDate

data class UserResponse (
    val email: String,
    val password: String,
    val username: String,
    val birth: LocalDate,
    val userRole: ROLE,
)