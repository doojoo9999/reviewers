package com.teamsparta.reviewers.domain.post.dto.request

data class UpdateReplyRequest (
    val content: String,
    val userId: Long,
    val postId: Long,
    val userName: String
)
