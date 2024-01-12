package com.teamsparta.reviewers.domain.post.dto.request

data class UpdateCommentRequest(
     val userId : Long,
     var name: String,
     var password: String,
     val content: String
)
