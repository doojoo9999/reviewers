package com.teamsparta.reviewers.domain.post.dto.request

data class UpdateCommentRequest(
     val userid: Long,
     var name: String,
     var password: String,
     val content: String
)
