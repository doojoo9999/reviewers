package com.teamsparta.reviewers.domain.post.dto.response

data class CommentResponse(
    val content: String,
    val userId: Long,
    val userName: String,
)
