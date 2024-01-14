package com.teamsparta.reviewers.domain.user.dto.request

import com.teamsparta.reviewers.domain.user.common.UserRole
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

    @Schema(description = "회원 프로필 이미지",
        example = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kaka[…]dn%2FbWz7HG%2FbtsC9BPiomM%2FDFL2H2sCgFKDtsJ9T39UC0%2Fimg.png")
    val profile_Image: String,

    @Schema(description = "회원 유형", example = "USER")
    val userRole: UserRole,
)