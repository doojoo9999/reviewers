package com.teamsparta.reviewers.domain.user.model

import com.teamsparta.reviewers.domain.user.common.UserRole
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "birth", nullable = false)
    var birth: String,

    @Column(name = "username", nullable = false)
    var userName: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    val userRole: UserRole = UserRole.USER,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userid: Long? = null
}

fun UserEntity.toSignUpResponse(): SignUpResponse {
    return SignUpResponse(
        password = password,
        email = email,
        birth = birth,
        userName = userName,
        userRole = userRole,
    )
}
