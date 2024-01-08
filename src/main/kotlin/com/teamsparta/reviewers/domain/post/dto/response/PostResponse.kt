package com.teamsparta.reviewers.domain.post.dto.response

data class PostResponse (
    val thumbnailUrl: String,
    val title: String,
    val content: String,
    val likes: Int,
)