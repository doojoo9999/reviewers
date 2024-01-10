package com.teamsparta.reviewers.domain.user.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CreateUserRequest(
    @field:NotBlank
    @field:Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{4,12}\$",
        message = "비밀번호는 최소 4자리 ~ 12자리, 영문과 숫자가 포함되어야 합니다."
    )
    @JsonProperty("password")
    private val _password: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^[\\\\w.-]+@[\\\\w.-]+\\\\.[A-Za-z]{2,}\$",
        message = "이메일 형식이 올바르지 않습니다."
    )
    @JsonProperty("email")
    private val _email: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^\\d{8}$",
        message = "생년월일은 숫자 8자리로 입력해야 합니다."
    )
    @JsonProperty("birth")
    private val _birth: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^[가-힣]+$",
        message = "이름은 한국어로만 입력해야 합니다."
    )
    @JsonProperty("username")
    private val _username: String?,

    ) {

    val password: String
        get() = _password!! // _password 는 null을 허용하는 타입이지만, password는 null을 허용하지 않는 타입 (string) 인 상태라 !!를 붙여줌
    val email: String
        get () = _email!!
    val birth: LocalDate
        get() = _birth!!.toLocalDate()
    val username: String
        get() = _username!!

    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this,  DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    // string을 localdate 형식으로 반환하기 위해 확장 함수 사용
}