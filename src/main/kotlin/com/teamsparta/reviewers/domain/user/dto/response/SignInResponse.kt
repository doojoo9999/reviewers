package com.teamsparta.reviewers.domain.user.dto.response

import com.teamsparta.reviewers.domain.user.common.UserRole
import io.swagger.v3.oas.annotations.media.Schema

data class SignInResponse(
    @Schema(description = "회원 메일", example = "example@mail.com")
    val email: String,

    @Schema(description = "회원 유형", example = "USER")
    val userRole: UserRole,
)