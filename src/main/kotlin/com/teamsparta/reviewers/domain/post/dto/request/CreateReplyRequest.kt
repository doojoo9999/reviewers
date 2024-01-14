package com.teamsparta.reviewers.domain.post.dto.request

data class CreateReplyRequest (
    val content : String,
    val userid: Long,
)