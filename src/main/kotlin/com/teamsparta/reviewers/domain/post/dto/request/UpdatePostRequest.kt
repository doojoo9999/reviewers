package com.teamsparta.reviewers.domain.post.dto.request

data class UpdatePostRequest(
    val thumbnailUrl: String,
    val title: String,
    val content: String,
)