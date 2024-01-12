package com.teamsparta.reviewers.domain.post.dto.response

data class CommentReplyResponse(
    val postid : Long,
    val userid : Long?,
    val parentCommentId : Long?
)
