package com.teamsparta.reviewers.domain.user.dto.request

import com.teamsparta.reviewers.domain.user.common.UserRole
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class SignUpRequest(

    @Schema(description = "회원 비밀번호", example = "pass1234")
    @NotBlank
    @field:Pattern(
        regexp = "^[a-zA-Z0-9!@#$%^&*]{8,16}$",
        message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다."
    )
    val password: String,

    @Schema(description = "회원 메일", example = "example@mail.com")
    @NotBlank
    @field:Email(message = "메일 주소 형식을 넣어주세요.")
    val email: String,

    @Schema(description = "회원 생년월일", example = "19990101")
    @NotBlank
    @field:Pattern(
        regexp = "[0-9]{8}$",
        message = "생년월일 8자리를 넣어주세요."
    )
    val birth: String,

    @Schema(description = "회원 이름", example = "홍길동")
    @field:NotEmpty(message = "이름은 비워둘 수 없습니다.")
    val userName: String,

    @Schema(description = "회원 프로필 이미지",
        example = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kaka[…]dn%2FbWz7HG%2FbtsC9BPiomM%2FDFL2H2sCgFKDtsJ9T39UC0%2Fimg.png")
    val profile_Image: String,

    @Schema(description = "회원 유형", example = "USER")
    val userRole: UserRole,
)