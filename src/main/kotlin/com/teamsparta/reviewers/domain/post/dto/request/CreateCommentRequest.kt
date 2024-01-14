package com.teamsparta.reviewers.domain.post.dto.request

data class CreateCommentRequest (
    val content: String,
    val userid: Long,
    val parentCommentId: Long,
    val userName: String
)