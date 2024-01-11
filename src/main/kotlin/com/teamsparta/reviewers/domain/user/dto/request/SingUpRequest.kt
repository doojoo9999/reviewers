package com.teamsparta.reviewers.domain.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class SingUpRequest(

    @NotBlank
    @field:Pattern(
        regexp = "^[a-zA-Z0-9!@#$%^&*]{8,16}$",
        message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다."
    )
    val password: String,

    @NotBlank
    @field:Email(message = "메일 주소 형식을 넣어주세요.")
    val email: String,

    @NotBlank
    @field:Pattern(
        regexp = "[0-9]{8}$",
        message = "생년월일 8자리를 넣어주세요."
    )
    val birth: String,

    @field:NotEmpty(message = "이름은 비워둘 수 없습니다.")
    val userName: String,

    val profile_Image: String,
)