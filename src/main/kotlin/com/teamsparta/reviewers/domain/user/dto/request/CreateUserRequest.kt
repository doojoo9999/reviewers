package com.teamsparta.reviewers.domain.user.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.teamsparta.reviewers.domain.user.model.ROLE
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CreateUserRequest(
    @field:NotBlank
    @field:Pattern(
        regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-z]).{8,}\$",
    //(?=.*\d): 어떤 위치에서든 숫자 (\d)가 적어도 하나 이상 있어야 함을 나타냅니다.
    //(?=.*[a-z]): 어떤 위치에서든 소문자 알파벳 (a-z)이 적어도 하나 이상 있어야 함을 나타냅니다.
    //(?=.*[a-zA-Z]): 어떤 위치에서든 알파벳 (a-z )이 적어도 하나 이상 있어야 함을 나타냅니다.
    //.{8,}: 적어도 8자 이상의 어떤 문자든 가능함을 나타냅니다.
        message = "비밀번호는 최소 8자리, 영문 대/소문자와 숫자가 포함되어야 합니다."
    )
    @JsonProperty("password")
    private val _password: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^(?:(?:[\\w`~!#\$%^&*\\-=+;:{}'|,?\\/]+(?:(?:\\.(?:\"(?:\\\\?[\\w`~!#\$%^&*\\-=+;:{}'|,?\\/\\.()<>\\[\\] @]|\\\\\"|\\\\\\\\)*\"|[\\w`~!#\$%^&*\\-=+;:{}'|,?\\/]+))*\\.[\\w`~!#\$%^&*\\-=+;:{}'|,?\\/]+)?)|(?:\"(?:\\\\?[\\w`~!#\$%^&*\\-=+;:{}'|,?\\/\\.()<>\\[\\] @]|\\\\\"|\\\\\\\\)+\"))@(?:[a-zA-Z\\d\\-]+(?:\\.[a-zA-Z\\d\\-]+)*|\\[\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\])\$",
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

    @JsonProperty("userRole")
    private val _userRole: ROLE,

    ) {

    val password: String
        get() = _password!! // _password 는 null을 허용하는 타입이지만, password는 null을 허용하지 않는 타입 (string) 인 상태라 !!를 붙여줌
    val email: String
        get () = _email!!
    val birth: LocalDate
        get() = _birth!!.toLocalDate()
    val username: String
        get() = _username!!

    val userRole: ROLE
        get() = _userRole

    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this,  DateTimeFormatter.ofPattern("yyyyMMdd"))
    // string을 localdate 형식으로 반환하기 위해 확장 함수 사용

}

