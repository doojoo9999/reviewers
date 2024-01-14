package com.teamsparta.reviewers.domain.post.dto.request

data class DeleteCommentRequest (
    val userid: Long,
    val postId : Long,
    val commentid : Long
)
