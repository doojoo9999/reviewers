package com.teamsparta.reviewers.domain.user.model

import com.teamsparta.reviewers.domain.user.dto.response.UserResponse
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name= "users")
class UserEntity(

    @Column(name = "email", updatable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "username", nullable = false)
    val username: String,

    @Column(name = "birth", nullable = false)
    val birth: LocalDate,

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    var userRole: ROLE
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    var userId: Long = 0
}

fun UserEntity.toResponse(): UserResponse {
    return UserResponse(
        email = email,
        birth = birth,
        password = password,
        username = username,
        userRole = userRole
    )
}