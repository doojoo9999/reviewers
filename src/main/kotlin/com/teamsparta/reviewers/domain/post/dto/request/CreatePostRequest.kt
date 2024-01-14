package com.teamsparta.reviewers.domain.post.dto.request

data class CreatePostRequest(
    val thumbnailUrl: String,
    val title: String,
    val content: String,
    val userid: Long,
)
