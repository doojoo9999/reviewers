package com.teamsparta.reviewers.domain.user.model

import com.teamsparta.reviewers.domain.post.model.CommentEntity
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val userCommentList: MutableList<CommentEntity> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userid: Long? = null
}

fun UserEntity.toResponse(): UserResponse {
    return UserResponse(
        password = password,
        email = email,
        birth = birth,
        userName = userName,
    )
}