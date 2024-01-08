package com.teamsparta.reviewers.domain.post.dto.request

data class CreateCommentRequest (
    val content: String,
    val userId: Long,
    val postid: Long,
    val userName: String,
)