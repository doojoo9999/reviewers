package com.teamsparta.reviewers.domain.post.dto.request

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator

data class UpdateCommentRequest(
    val postId : String,
    val userId: Long,
    val content: String
)

