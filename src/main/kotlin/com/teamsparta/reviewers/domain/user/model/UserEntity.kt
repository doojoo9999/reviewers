package com.teamsparta.reviewers.domain.user.model

import com.teamsparta.reviewers.domain.post.model.CommentEntity
import com.teamsparta.reviewers.domain.user.common.UserRole
import com.teamsparta.reviewers.domain.user.dto.request.UserUpdateRequest
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import com.teamsparta.reviewers.domain.user.dto.response.UserUpdateResponse
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

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

    @Column(name = "profile_image", nullable = false)
    var profile_Image: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    var userRole: UserRole = UserRole.USER,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val userCommentList: MutableList<CommentEntity> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userid: Long? = null
}

fun UserEntity.toSignUpResponse(): SignUpResponse {
    return SignUpResponse(
        email = email,
        birth = birth,
        userName = userName,
        userRole = userRole,
        profile_Image = profile_Image,
    )
}