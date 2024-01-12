package com.teamsparta.reviewers.domain.post.dto.request

data class DeleteReplyRequest (
    val userId : Long,
    val postId : Long,
    val commentid : Long,
    val content: String,
    val userName: String
)