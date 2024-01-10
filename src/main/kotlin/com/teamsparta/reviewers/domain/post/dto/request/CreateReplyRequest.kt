package com.teamsparta.reviewers.domain.post.dto.request

data class CreateReplyRequest (
    val content : String,
    val userId : Long,
    val postId : Long,
    val commentId : Long,
    val userName : String
)