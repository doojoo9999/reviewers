package com.teamsparta.reviewers.domain.post.model

import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class CommentEntity (
    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "username", nullable = false)
    var userName: String,

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    var user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "postid", nullable = false)
    var post: PostEntity,

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    var parentComment: CommentEntity? = null,

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val commentList: MutableList<CommentEntity> = mutableListOf()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    var commentid: Long? = null
}

fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        content = content,
        userName = userName,
    )
}