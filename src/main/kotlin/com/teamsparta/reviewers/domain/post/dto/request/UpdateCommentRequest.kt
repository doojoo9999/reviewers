package com.teamsparta.reviewers.domain.post.dto.request

data class UpdateCommentRequest(
    var name: String,
    var password: String,
    val content: String
)