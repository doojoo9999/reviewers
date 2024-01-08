package com.teamsparta.reviewers.domain.post.model

import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class CommentEntity (
    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "userid", nullable = false)
    var userId: Long,

    @Column(name = "username", nullable = false)
    var userName: String,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    var post: PostEntity,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        content = content,
        userId = userId,
        userName = userName
    )
}