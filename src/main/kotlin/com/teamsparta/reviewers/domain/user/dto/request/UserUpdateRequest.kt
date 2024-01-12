package com.teamsparta.reviewers.domain.user.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class UserUpdateRequest(

    @Schema(description = "회원 비밀번호", example = "pass1234")
    var password: String,

    @Schema(description = "새 회원 비밀번호", example = "pass1234")
    var newPassword: String,

    @Schema(description = "회원 이름", example = "홍길동")
    val userName: String,

    @Schema(description = "회원 생년월일", example = "19990101")
    val birth: String,

    @Schema(description = "회원 프로필 이미지")
    val profile_Image: String,
)