package com.teamsparta.reviewers.domain.user.dto.response

import com.teamsparta.reviewers.domain.user.common.UserRole
import io.swagger.v3.oas.annotations.media.Schema

data class SignUpResponse(

    @Schema(description = "회원 메일", example = "example@mail.com")
    val email: String,

    @Schema(description = "회원 생년월일", example = "19990101")
    val birth: String,

    @Schema(description = "회원 이름", example = "홍길동")
    val userName: String,

    @Schema(description = "회원 유형", example = "USER")
    val userRole: UserRole,

    @Schema(description = "회원 프로필 이미지")
    val profileImage: String,
)