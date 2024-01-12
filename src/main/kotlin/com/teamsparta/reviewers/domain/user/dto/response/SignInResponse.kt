package com.teamsparta.reviewers.domain.user.dto.response

import com.teamsparta.reviewers.domain.user.common.UserRole
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import org.springframework.boot.web.server.Cookie

data class SignInResponse(
    @Schema(description = "회원 메일", example = "example@mail.com")
    val email: String,

    @Schema(description = "회원 이름", example = "홍길동")
    val userName: String,

    @Schema(description = "회원 유형", example = "USER")
    val userRole: UserRole,

    val token: String,
)