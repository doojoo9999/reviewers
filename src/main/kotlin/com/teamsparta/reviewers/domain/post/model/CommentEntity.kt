package com.teamsparta.reviewers.domain.post.model

import com.teamsparta.reviewers.domain.post.dto.response.CommentReplyResponse
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class CommentEntity(
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
    var parentComment: CommentEntity? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    var commentid: Long? = null

    // 대댓글 지우기도 댓글 지우기와 동일하기 때문에 reply등을 새로 만들 필요 없이 commentid 재활용해보자
}

fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        content = content,
        userName = userName
    )
}

fun CommentEntity.toReplyResponse(): CommentReplyResponse {
    return CommentReplyResponse(
        postid = post.postid,
        userid = user.userid,
        parentCommentId = parentComment?.commentid
    )
}