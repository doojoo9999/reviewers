package com.teamsparta.reviewers.domain.post.dto.request

data class DeleteCommentRequest (
    val userId : Long,
    val postId : Long,
    val commentid : Long
)